package latibro.automation

import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardItem
import latibro.automation.base.location.block.reference.card.BlockLocationReferenceSourceCardItem
import latibro.automation.base.server.card.ServerSourceCardItem
import latibro.automation.base.world.reference.card.WorldReferenceSourceCardItem
import latibro.devoplment.DebugToolItem
import latibro.devoplment.EntityMountToolItem
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

class ModItems {

    @SideOnly(Side.CLIENT)
    static void initModels() {
        AutomationMod.logger.debug("ModItems.initModels")

        ServerSourceCardItem.INSTANCE.initModel()
        WorldReferenceSourceCardItem.INSTANCE.initModel()
        EntityReferenceSourceCardItem.INSTANCE.initModel()
        BlockLocationReferenceSourceCardItem.INSTANCE.initModel()
    }

    static void registerItems(IForgeRegistry<Item> registry) {
        AutomationMod.logger.debug("ModItems.registerItems")

        registry.register(ServerSourceCardItem.INSTANCE)
        registry.register(WorldReferenceSourceCardItem.INSTANCE)
        registry.register(EntityReferenceSourceCardItem.INSTANCE)
        registry.register(BlockLocationReferenceSourceCardItem.INSTANCE)

        registerItem(registry, new DebugToolItem())
        registerItem(registry, new EntityMountToolItem())
    }

    static void registerItem(IForgeRegistry<Item> registry, Item item) {
        registry.register(item)
    }

}
