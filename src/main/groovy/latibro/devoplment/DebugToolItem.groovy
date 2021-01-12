package latibro.devoplment

import groovy.transform.CompileStatic
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World

@CompileStatic
class DebugToolItem extends Item {

    DebugToolItem() {
        super();
        setRegistryName("debug_tool");
        setUnlocalizedName("development.debug_tool");
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);
    }

    @Override
    boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity target) {
        printEntityDetails(player, target)
        return true;
    }

    @Override
    boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        printEntityDetails(player, target)
        return true;
    }

    @Override
    EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        printBlockDetails(player, world, blockPos)
        return EnumActionResult.SUCCESS
    }

    static void printEntityDetails(EntityPlayer player, Entity target) {
        player.sendMessage(new TextComponentString("Entity.UUID: " + target.getUniqueID()));
    }

    static void printBlockDetails(EntityPlayer player, World world, BlockPos blockPos) {
        player.sendMessage(new TextComponentString("BlockPos: " + blockPos.toString()));
    }

}
