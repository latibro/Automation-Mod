package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

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
        storeBlockPos(player.getHeldItem(hand), player, blockPos)
        return EnumActionResult.SUCCESS
    }

    private static void storeBlockPos(ItemStack itemStack, EntityPlayer player, BlockPos blockPos) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound())
        }
        itemStack.getTagCompound().setLong("location", blockPos.toLong())
        player.setHeldItem(EnumHand.MAIN_HAND, itemStack)
        player.sendMessage(new TextComponentString("Location stored: " + blockPos))
    }

}
