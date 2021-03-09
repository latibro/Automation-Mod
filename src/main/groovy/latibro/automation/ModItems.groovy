package latibro.automation

import latibro.automation.linkbox.entity.EntityLinkCardItem
import latibro.automation.linkbox.location.LocationLinkCardItem
import latibro.devoplment.DebugToolItem
import latibro.devoplment.EntityMountToolItem
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

class ModItems {

    @GameRegistry.ObjectHolder("automation:entity_link_card")
    static EntityLinkCardItem entityLinkCard

    @GameRegistry.ObjectHolder("automation:location_link_card")
    static LocationLinkCardItem locationLinkCard

    @SideOnly(Side.CLIENT)
    static void initModels() {
        AutomationMod.logger.debug("ModItems.initModels")

        entityLinkCard.initModel()
        locationLinkCard.initModel()
    }

    static void registerItems(IForgeRegistry<Item> registry) {
        AutomationMod.logger.debug("ModItems.registerItems")

        registerItem(registry, new EntityLinkCardItem())
        registerItem(registry, new LocationLinkCardItem())

        registerItem(registry, new DebugToolItem())
        registerItem(registry, new EntityMountToolItem())
    }

    static void registerItem(IForgeRegistry<Item> registry, Item item) {
        registry.register(item)
    }

}
