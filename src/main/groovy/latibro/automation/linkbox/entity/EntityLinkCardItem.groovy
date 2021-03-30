package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import latibro.automation.proxy.ScreenProxy
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTUtil
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
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
        if (player.world.isRemote) {
            return true
        }

        storeEntityUUID(itemStack, player, target)
        return true
    }

    @Override
    boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (player.world.isRemote) {
            return true
        }

        storeEntityUUID(itemStack, player, target)
        return true
    }

    @Override
    ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
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
        def uuid = getEntityUUID(itemStack)
        if (uuid) {
            tooltip.add("Entity ID: " + uuid.toString())
        }
    }

    static UUID getEntityUUID(ItemStack itemStack) {
        def nbt = itemStack.getTagCompound()
        def automationNbt = nbt?.getCompoundTag("automation")
        if (!automationNbt?.hasKey("entity")) {
            return null
        }
        def entityNbt = automationNbt?.getCompoundTag("entity")
        def entityUuid = NBTUtil.getUUIDFromTag(entityNbt)
        return entityUuid
    }

    static UUID getEntityUUID(EntityPlayer player) {
        def itemStack = player.getHeldItem(EnumHand.MAIN_HAND)
        def uuid = getEntityUUID(itemStack)
        return uuid
    }

    static ItemStack setEntityUUID(ItemStack itemStack, UUID entityUuid) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound())
        }
        def nbt = itemStack.getTagCompound()
        if (!nbt.hasKey("automation")) {
            nbt.setTag("automation", new NBTTagCompound())
        }
        def automationNbt = nbt.getCompoundTag("automation")
        def entityNbt = NBTUtil.createUUIDTag(entityUuid)
        automationNbt.setTag("entity", entityNbt)
        return itemStack
    }

    static void setEntityUUID(EntityPlayer player, UUID entityUuid) {
        def itemStack = player.getHeldItem(EnumHand.MAIN_HAND)
        itemStack = setEntityUUID(itemStack, entityUuid)
        player.setHeldItem(EnumHand.MAIN_HAND, itemStack)
        player.inventoryContainer.detectAndSendChanges()
    }

    private static void storeEntityUUID(ItemStack itemStack, EntityPlayer player, Entity target) {
        def uuid = target.getUniqueID()
        setEntityUUID(player, uuid)
        player.sendMessage(new TextComponentString("Entity ID stored: " + uuid))
    }

}
