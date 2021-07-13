package latibro.automation.nativeimpl.context.entity.multi


import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.entity.multi.AbstractEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.entity.InstanceCoreEntityLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.server.DefaultCoreServerLinkContext
import net.minecraft.entity.Entity

abstract class CoreEntityMultiLinkContext extends AbstractEntityMultiLinkContext {

    abstract List<Entity> getNativeEntityList()

    @Override
    boolean isLinked() {
        return nativeEntityList
    }

    @Override
    int count() {
        return nativeEntityList.size()
    }

    @Override
    List<EntityLinkContext> asList() {
        return wrapNativeEntityList(nativeEntityList)
    }

    @Override
    List<CoreEntityLinkContext> asList(int maxCount) {
        return wrapNativeEntityList(nativeEntityList.take(maxCount))
    }

    @Override
    CoreServerLinkContext getServer() {
        return new DefaultCoreServerLinkContext()
    }

    protected static CoreEntityLinkContext wrapNativeEntity(Entity nativeEntity) {
        return new InstanceCoreEntityLinkContext(nativeEntity)
    }

    protected static List<CoreEntityLinkContext> wrapNativeEntityList(List<Entity> nativeEntityList) {
        return nativeEntityList.collect {
            wrapNativeEntity(it)
        }
    }

}
