package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModCreativeTabs
import latibro.automation.proxy.NetworkProxy
import latibro.automation.proxy.ScreenProxy
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTUtil
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
class LocationLinkCardItem extends Item {

    LocationLinkCardItem() {
        setRegistryName("location_link_card")
        setUnlocalizedName("automation.location_link_card")
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

        setLocation(player, pickedBlockPos)

        player.sendMessage(new TextComponentString("Location stored: " + blockPos))
        return EnumActionResult.SUCCESS
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

        ScreenProxy.openHeldItemScreen(player)

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        def location = getLocation(itemStack)
        if (location) {
            tooltip.add("Location: X: " + location.x + ", Y: " + location.y + ", Z: " + location.z)
        }
    }

    static BlockPos getLocation(ItemStack itemStack) {
        if (itemStack.item !instanceof LocationLinkCardItem) {
            throw new IllegalArgumentException()
        }
        def nbt = itemStack.getTagCompound()
        def automationNbt = nbt?.getCompoundTag("automation")
        if (!automationNbt?.hasKey("location")) {
            return null
        }
        def locationNbt = automationNbt?.getCompoundTag("location")
        def location = NBTUtil.getPosFromTag(locationNbt)
        return location
    }

    private static ItemStack writeLocationToNBT(ItemStack itemStack, BlockPos location) {
        if (itemStack.item !instanceof LocationLinkCardItem) {
            throw new IllegalArgumentException()
        }
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound())
        }
        def nbt = itemStack.getTagCompound()
        if (!nbt.hasKey("automation")) {
            nbt.setTag("automation", new NBTTagCompound())
        }
        def automationNbt = nbt.getCompoundTag("automation")
        def locationNbt = NBTUtil.createPosTag(location)
        automationNbt.setTag("location", locationNbt)
        return itemStack
    }

    static BlockPos getLocation(EntityPlayer player) {
        def itemStack = player.getHeldItem(EnumHand.MAIN_HAND)
        if (itemStack?.item instanceof LocationLinkCardItem) {
            def location = getLocation(itemStack)
            return location
        }
        return null
    }

    static void setLocation(EntityPlayer player, BlockPos location) {
        def itemStack = player.getHeldItem(EnumHand.MAIN_HAND)
        if (itemStack?.item instanceof LocationLinkCardItem) {
            if (player.world.isRemote) {
                NetworkProxy.sendMessageToServer(new LocationLinkCardMessage(location))
            } else {
                itemStack = writeLocationToNBT(itemStack, location)
                player.setHeldItem(EnumHand.MAIN_HAND, itemStack)
                player.inventoryContainer.detectAndSendChanges()
            }
        }
    }

}
