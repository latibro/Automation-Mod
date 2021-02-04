package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.nativeimpl.context.location.NativeEntityLocationContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import latibro.automation.nativeimpl.context.server.NativeEntityServerContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import latibro.automation.nativeimpl.context.world.NativeEntityWorldContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext

@CompileStatic
abstract class AbstractNativeEntityContext implements NativeEntityContext {

    @Override
    NativeWorldContext getWorldContext() {
        return new NativeEntityWorldContext(this)
    }

    @Override
    NativeServerContext getServerContext() {
        return new NativeEntityServerContext(this)
    }

    @Override
    NativeLocationContext getLocationContext() {
        return new NativeEntityLocationContext(this)
    }

    @Override
    UUID getUUID() {
        return nativeEntity.uniqueID
    }

    @Override
    boolean isLoaded() {
        return nativeEntity.isAddedToWorld()
    }

    @Override
    String getName() {
        return nativeEntity.getName()
    }

}
