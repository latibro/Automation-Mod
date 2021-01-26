package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.entity.AbstractEntityContext
import latibro.automation.nativeimpl.context.position.NativeEntityPositionContext
import latibro.automation.core.context.position.PositionContext
import latibro.automation.nativeimpl.context.server.NativeEntityServerContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.nativeimpl.context.world.NativeEntityWorldContext
import latibro.automation.core.context.world.WorldContext

@CompileStatic
abstract class AbstractNativeEntityContext extends AbstractEntityContext implements NativeEntityContext {

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
    UUID getEntityUUID() {
        return nativeEntity.uniqueID
    }

    @Override
    boolean isEntityLoaded() {
        return nativeEntity.isAddedToWorld()
    }

}
