package latibro.devoplment

import groovy.transform.CompileStatic
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
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

@CompileStatic
class EntityMountToolItem extends Item {

    EntityMountToolItem() {
        super();
        setRegistryName("entity_mount_tool");
        setUnlocalizedName("development.entity_mount_tool");
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);
    }

    @Override
    boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity target) {
        return useTool(itemStack, player, target, EnumHand.MAIN_HAND);
    }

    @Override
    boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        return useTool(itemStack, player, target, hand)
    }

    boolean useTool(ItemStack itemStack, EntityPlayer player, Entity target, EnumHand hand) {
        ItemStack tool = player.getHeldItem(hand)

        if (tool.getItem() != this) {
            player.sendMessage(new TextComponentString("Not the tool"))
            return true
        }

        if (!tool.hasTagCompound()) {
            tool.setTagCompound(new NBTTagCompound())
        }

        if (player.isSneaking()) {
            target.dismountRidingEntity()
            player.sendMessage(new TextComponentString("Dismounted rider"))
            return true
        }

        if (!tool.getTagCompound().hasKey("rider")) {
            // add rider to tool
            tool.getTagCompound().setString("rider", target.uniqueID.toString())
            player.setHeldItem(hand, tool);

            player.sendMessage(new TextComponentString("Stored rider: " + target.getUniqueID()))
        } else {
            // find rider
            UUID riderUUID = UUID.fromString(tool.getTagCompound().getString("rider"))
            Entity rider = target.world.loadedEntityList.find { it.uniqueID == riderUUID }

            if (rider == target) {
                player.sendMessage(new TextComponentString("Failed to mount self"))
                return true
            }

            // try to mount rider to target
            boolean riding = rider.startRiding(target)

            if (riding) {
                // clear tool
                tool.getTagCompound().removeTag("rider")
                player.setHeldItem(hand, tool);

                player.sendMessage(new TextComponentString("Mounted rider"))
            } else {
                player.sendMessage(new TextComponentString("Failed to mount rider"))
            }
        }
        return true
    }

    @Override
    EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack tool = player.getHeldItem(hand)

        if (tool.getItem() != this) {
            player.sendMessage(new TextComponentString("Not the tool"));
            return EnumActionResult.SUCCESS
        }

        if (!tool.hasTagCompound()) {
            tool.setTagCompound(new NBTTagCompound());
        }

        // clear tool
        tool.getTagCompound().removeTag("rider")
        player.setHeldItem(hand, tool);

        player.sendMessage(new TextComponentString("Cleared rider"));
        return EnumActionResult.SUCCESS
    }

}
