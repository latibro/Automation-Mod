package latibro.automation.nativeimpl.context.entity.multi


import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.entity.Entity

@CompileStatic
final class WorldLoadedCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreWorldLinkContext world

    WorldLoadedCoreEntityMultiLinkContext(CoreWorldLinkContext world) {
        this.world = Objects.requireNonNull(world)
    }

    List<Entity> getNativeEntityList() {
        def fields = world.nativeWorld.class.getFields()
        AutomationMod.logger.info("### fields count {}", fields.length)
        for (def field : fields) {
            AutomationMod.logger.info("### ------")
            AutomationMod.logger.info("### Field {}", field)
            AutomationMod.logger.info("### Field.name {}", field.name)
            AutomationMod.logger.info("### Field.type {}", field.type)
        }

        def methods = world.nativeWorld.class.getMethods()
        AutomationMod.logger.info("### methods count {}", methods.length)
        for (def method : methods) {
            AutomationMod.logger.info("### ------")
            AutomationMod.logger.info("### Method {}", method)
            AutomationMod.logger.info("### Method.name {}", method.name)
            AutomationMod.logger.info("### Method.returnType {}", method.returnType)
        }

        def fieldX = world.nativeWorld.@loadedEntityList
        AutomationMod.logger.info("### fieldX {}", fieldX)

        def methodX = world.nativeWorld.loadedEntityList
        AutomationMod.logger.info("### methodX {}", methodX)

        def methodY = world.nativeWorld.getLoadedEntityList()
        AutomationMod.logger.info("### methodY {}", methodY)

        def loadedEntityList = world.nativeWorld.@loadedEntityList
        return loadedEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
