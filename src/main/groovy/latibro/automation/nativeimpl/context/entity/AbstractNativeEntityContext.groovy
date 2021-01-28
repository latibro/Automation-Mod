package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext
import latibro.automation.nativeimpl.context.position.NativeEntityPositionContext
import latibro.automation.nativeimpl.context.server.NativeEntityServerContext
import latibro.automation.nativeimpl.context.world.NativeEntityWorldContext

@CompileStatic
abstract class AbstractNativeEntityContext implements NativeEntityContext {

    @Override
    WorldContext getWorldContext() {
        return new NativeEntityWorldContext(this)
    }

    @Override
    ServerContext getServerContext() {
        return new NativeEntityServerContext(this)
    }

    @Override
    PositionContext getPositionContext() {
        return new NativeEntityPositionContext(this)
    }

    @Override
    UUID getUUID() {
        return nativeEntity.uniqueID
    }

    @Override
    boolean isLoaded() {
        return nativeEntity.isAddedToWorld()
    }

}
