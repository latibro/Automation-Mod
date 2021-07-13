package latibro.automation.base.entity.reference.card.item

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import latibro.automation.base.entity.reference.EntityReference
import latibro.automation.core.source.reference.card.item.SourceReferenceSourceCardItem
import latibro.automation.core.util.NBTHelper
import latibro.automation.proxy.ScreenProxy
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
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
class EntityReferenceSourceCardItem extends SourceReferenceSourceCardItem<EntityReference> {

    static final EntityReferenceSourceCardItem INSTANCE = new EntityReferenceSourceCardItem()

    private EntityReferenceSourceCardItem() {
        setRegistryName("entity-reference-source-card")
        setUnlocalizedName("automation.entity-reference-source-card")
        setCreativeTab(ModCreativeTabs.DEFAULT)
        setMaxStackSize(1)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    @Override
    boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity target) {
        updateSourceReference(itemStack, player, target)
        return true
    }

    @Override
    boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        updateSourceReference(itemStack, player, target)
        return true
    }

    private void updateSourceReference(ItemStack itemStack, EntityPlayer player, Entity target) {
        if (player.world.isRemote) {
            // Do nothing on client side
            return
        }

        def sourceReference = EntityReference.create(target)
        setSourceReference(itemStack, sourceReference)
        player.inventoryContainer.detectAndSendChanges()
        player.sendMessage(new TextComponentString("Entity ID stored: " + sourceReference.entityUuid))
    }

    @Override
    ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        def itemStack = player.getHeldItem(hand)

        if (hand != EnumHand.MAIN_HAND) {
            // Only handle if in main hand
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
        def entityUuid = getReferenceEntityUuid(itemStack)
        tooltip.add("Entity ID: " + entityUuid)
    }

    void setReferenceEntityUuid(ItemStack itemStack, String uuid) {
        def nbt = NBTHelper.getOrCreateCompoundTag(itemStack, "automation.source.reference")
        if (uuid) {
            nbt.setString("uuid", uuid)
        } else {
            nbt.removeTag("uuid")
        }
    }

    String getReferenceEntityUuid(ItemStack itemStack) {
        def nbt = NBTHelper.getCompoundTag(itemStack, "automation.source.reference")
        if (nbt?.hasKey("uuid")) {
            return nbt.getString("uuid")
        } else {
            return null
        }
    }

    @Override
    void setSourceReference(ItemStack itemStack, EntityReference sourceReference) {
        setReferenceEntityUuid(itemStack, sourceReference.entityUuid.toString())
    }

    @Override
    EntityReference getSourceReference(ItemStack itemStack) {
        def uuid = getReferenceEntityUuid(itemStack)
        def sourceReference = EntityReference.create(uuid)
        return sourceReference
    }

}
