package latibro.automation.nativeimpl.context.server


import latibro.automation.nativeimpl.context.entity.group.NativeEntityGroupContext
import latibro.automation.nativeimpl.context.entity.group.NativeServerLoadedEntityGroupContext

abstract class AbstractNativeServerContext implements NativeServerContext {

    @Override
    NativeEntityGroupContext getLoadedEntitiesContext() {
        return new NativeServerLoadedEntityGroupContext(this)
    }

}