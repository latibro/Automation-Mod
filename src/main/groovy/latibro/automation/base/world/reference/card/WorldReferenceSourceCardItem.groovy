package latibro.automation.base.world.reference.card

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModCreativeTabs
import latibro.automation.base.world.reference.WorldReference
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
class WorldReferenceSourceCardItem extends SourceReferenceSourceCardItem<WorldReference> {

    static final WorldReferenceSourceCardItem INSTANCE = new WorldReferenceSourceCardItem()

    WorldReferenceSourceCardItem() {
        setRegistryName("world-reference-source-card")
        setUnlocalizedName("automation.world-reference-source-card")
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

        def itemStack = player.getHeldItem(hand)
        updateSourceReference(player, itemStack, world)

        return EnumActionResult.SUCCESS
    }

    private void updateSourceReference(EntityPlayer player, ItemStack itemStack, World world) {
        if (player.world.isRemote) {
            // Do nothing on client side
            return
        }

        def sourceReference = WorldReference.create(world)
        setSourceReference(itemStack, sourceReference)
        player.inventoryContainer.detectAndSendChanges()
        player.sendMessage(new TextComponentString("World stored: " + sourceReference.worldName))
    }

    @Override
    ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        AutomationMod.logger.info("onItemRightClick")
        def itemStack = player.getHeldItem(hand)

        if (hand != EnumHand.MAIN_HAND) {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
        }

        openScreen(player)

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }

    private static void openScreen(EntityPlayer player) {
        if (player.world.isRemote) {
            // Do nothing on client side
            return
        }

        ScreenProxy.openHeldItemScreen(player)
    }

    @Override
    @SideOnly(Side.CLIENT)
    void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        def worldName = getReferenceWorldName(itemStack)
        tooltip.add("World: " + worldName)
    }

    void setReferenceWorldName(ItemStack itemStack, String worldName) {
        def nbt = NBTHelper.getOrCreateCompoundTag(itemStack, "automation.source.reference")
        if (worldName) {
            nbt.setString("name", worldName)
        } else {
            nbt.removeTag("name")
        }
    }

    String getReferenceWorldName(ItemStack itemStack) {
        def nbt = NBTHelper.getCompoundTag(itemStack, "automation.source.reference")
        if (nbt?.hasKey("name")) {
            return nbt.getString("name")
        } else {
            return null
        }
    }

    void setSourceReference(ItemStack itemStack, WorldReference sourceReference) {
        setReferenceWorldName(itemStack, sourceReference.worldName)
    }

    WorldReference getSourceReference(ItemStack itemStack) {
        def worldName = getReferenceWorldName(itemStack)
        def sourceReference = WorldReference.create(worldName)
        return sourceReference
    }

}
