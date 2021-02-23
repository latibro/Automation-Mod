package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import latibro.automation.nativeimpl.context.location.EntityCoreLocationLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.server.EntityCoreServerLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import latibro.automation.nativeimpl.context.world.EntityCoreWorldLinkContext
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList

@CompileStatic
abstract class CoreEntityLinkContext implements EntityLinkContext, CoreContext {

    abstract Entity getNativeEntity()

    @Override
    CoreWorldLinkContext getWorld() {
        return new EntityCoreWorldLinkContext(this)
    }

    @Override
    CoreServerLinkContext getServer() {
        return new EntityCoreServerLinkContext(this)
    }

    @Override
    CoreLocationLinkContext getLocation() {
        return new EntityCoreLocationLinkContext(this)
    }

    @Override
    boolean isLinked() {
        return nativeEntity
    }

    @Override
    UUID getUUID() {
        return nativeEntity.uniqueID
    }

    @Override
    boolean isLoaded() {
        return isLinked()
    }

    @Override
    String getName() {
        return nativeEntity.name
    }

    @Override
    String getType() {
        return EntityList.getKey(nativeEntity)
    }

}
