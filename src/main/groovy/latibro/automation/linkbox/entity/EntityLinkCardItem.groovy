package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumHand
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@CompileStatic
class EntityLinkCardItem extends Item {

    EntityLinkCardItem() {
        setRegistryName("entity_link_card")
        setUnlocalizedName("automation.entity_link_card")
        setCreativeTab(ModCreativeTabs.DEFAULT)
        setMaxStackSize(1)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    @Override
    boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity target) {
        storeEntityUUID(itemStack, player, target)
        return true
    }

    @Override
    boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        storeEntityUUID(itemStack, player, target)
        return true
    }

    private static void storeEntityUUID(ItemStack itemStack, EntityPlayer player, Entity target) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        itemStack.getTagCompound().setString("entityUUID", String.valueOf(target.getUniqueID()));
        player.setHeldItem(EnumHand.MAIN_HAND, itemStack);
        player.sendMessage(new TextComponentString("Entity ID stored: " + target.getUniqueID()));
    }

    @Override
    void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
    }
}
