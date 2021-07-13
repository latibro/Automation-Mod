package latibro.automation.base.location.block.reference.card

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModCreativeTabs
import latibro.automation.base.location.block.reference.BlockLocationReference
import latibro.automation.core.source.reference.card.item.SourceReferenceSourceCardItem
import latibro.automation.core.util.NBTHelper
import latibro.automation.proxy.ScreenProxy
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@CompileStatic
class BlockLocationReferenceSourceCardItem extends SourceReferenceSourceCardItem<BlockLocationReference> {

    static final BlockLocationReferenceSourceCardItem INSTANCE = new BlockLocationReferenceSourceCardItem()

    BlockLocationReferenceSourceCardItem() {
        setRegistryName("block-location-reference-source-card")
        setUnlocalizedName("automation.block-location-reference-source-card")
        setCreativeTab(ModCreativeTabs.DEFAULT)
        setMaxStackSize(1)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    @Override
    EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (hand != EnumHand.MAIN_HAND) {
            return EnumActionResult.PASS
        }

        if (world.isRemote) {
            return EnumActionResult.SUCCESS
        }

        def pickedBlockPos
        if (player.isSneaking()) {
            pickedBlockPos = blockPos
        } else {
            pickedBlockPos = blockPos.offset(facing)
        }

        def itemStack = player.getHeldItem(hand)

        updateSourceReference(player, itemStack, world, pickedBlockPos)

        return EnumActionResult.SUCCESS
    }

    private void updateSourceReference(EntityPlayer player, ItemStack itemStack, World world, BlockPos blockPos) {
        if (player.world.isRemote) {
            // Do nothing on client side
            return
        }

        def sourceReference = BlockLocationReference.create(world, blockPos)
        setSourceReference(itemStack, sourceReference)
        player.inventoryContainer.detectAndSendChanges()
        player.sendMessage(new TextComponentString("Location stored: " + blockPos))
    }

    @Override
    ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        AutomationMod.logger.info("onItemRightClick")
        def itemStack = player.getHeldItem(hand)

        if (hand != EnumHand.MAIN_HAND) {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
        }

        if (world.isRemote) {
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
        }

        ScreenProxy.openHeldItemScreen(player) //TODO add hand as input

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        def worldName = getReferenceWorldName(itemStack)
        tooltip.add("World: ${worldName}" as String)
        def coordinateX = getReferenceCoordinateX(itemStack)
        def coordinateY = getReferenceCoordinateY(itemStack)
        def coordinateZ = getReferenceCoordinateZ(itemStack)
        tooltip.add("Coordinates: ${coordinateX}, ${coordinateY}, ${coordinateZ}" as String)
    }

    void setReferenceWorldName(ItemStack itemStack, String worldName) {
        def nbt = NBTHelper.getOrCreateCompoundTag(itemStack, "automation.source.reference.world")
        if (worldName) {
            nbt.setString("name", worldName)
        } else {
            nbt.removeTag("name")
        }
    }

    String getReferenceWorldName(ItemStack itemStack) {
        def nbt = NBTHelper.getCompoundTag(itemStack, "automation.source.reference.world")
        if (nbt?.hasKey("name")) {
            return nbt.getString("name")
        } else {
            return null
        }
    }

    void setReferenceCoordinateX(ItemStack itemStack, Integer coordinate) {
        def nbt = NBTHelper.getOrCreateCompoundTag(itemStack, "automation.source.reference.coordinates")
        if (coordinate) {
            nbt.setInteger("x", coordinate)
        } else {
            nbt.removeTag("x")
        }
    }

    Integer getReferenceCoordinateX(ItemStack itemStack) {
        def nbt = NBTHelper.getCompoundTag(itemStack, "automation.source.reference.coordinates")
        if (nbt?.hasKey("x")) {
            return nbt.getInteger("x")
        } else {
            return null
        }
    }

    void setReferenceCoordinateY(ItemStack itemStack, Integer coordinate) {
        def nbt = NBTHelper.getOrCreateCompoundTag(itemStack, "automation.source.reference.coordinates")
        if (coordinate) {
            nbt.setInteger("y", coordinate)
        } else {
            nbt.removeTag("y")
        }
    }

    Integer getReferenceCoordinateY(ItemStack itemStack) {
        def nbt = NBTHelper.getCompoundTag(itemStack, "automation.source.reference.coordinates")
        if (nbt?.hasKey("y")) {
            return nbt.getInteger("y")
        } else {
            return null
        }
    }

    void setReferenceCoordinateZ(ItemStack itemStack, Integer coordinate) {
        def nbt = NBTHelper.getOrCreateCompoundTag(itemStack, "automation.source.reference.coordinates")
        if (coordinate) {
            nbt.setInteger("z", coordinate)
        } else {
            nbt.removeTag("z")
        }
    }

    Integer getReferenceCoordinateZ(ItemStack itemStack) {
        def nbt = NBTHelper.getCompoundTag(itemStack, "automation.source.reference.coordinates")
        if (nbt?.hasKey("z")) {
            return nbt.getInteger("z")
        } else {
            return null
        }
    }

    @Override
    void setSourceReference(ItemStack itemStack, BlockLocationReference sourceReference) {
        setReferenceWorldName(itemStack, sourceReference.worldReference.worldName)
        setReferenceCoordinateX(itemStack, sourceReference.coordinateX)
        setReferenceCoordinateY(itemStack, sourceReference.coordinateY)
        setReferenceCoordinateZ(itemStack, sourceReference.coordinateZ)
    }

    @Override
    BlockLocationReference getSourceReference(ItemStack itemStack) {
        def worldName = getReferenceWorldName(itemStack)
        def coordinateX = getReferenceCoordinateX(itemStack)
        def coordinateY = getReferenceCoordinateX(itemStack)
        def coordinateZ = getReferenceCoordinateX(itemStack)
        def sourceReference = BlockLocationReference.create(worldName, coordinateX, coordinateY, coordinateZ)
        return sourceReference
    }

}
