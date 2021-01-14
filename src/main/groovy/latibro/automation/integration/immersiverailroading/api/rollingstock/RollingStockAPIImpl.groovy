package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import cam72cam.mod.entity.ModdedEntity
import groovy.transform.CompileStatic
import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI
import latibro.automation.integration.minecraft.api.entity.EntityAPI

import javax.annotation.Nonnull

@CompileStatic
class RollingStockAPIImpl extends AbstractHostedAPI implements RollingStockAPI {

    RollingStockAPIImpl(@Nonnull APIHost host) {
        super(host)
    }

    @Override
    List<String> getAllLoadedAsUUIDString() {
        return getAllLoadedAsUUID().collect { it.toString() }
    }

    List<UUID> getAllLoadedAsUUID() {
        return getAllLoaded().collect { it.getUUID() }
    }

    List<RollingStock> getAllLoaded() {
        return host.minecraftWorld.loadedEntityList.findAll {
            return it instanceof ModdedEntity && it.self instanceof EntityRollingStock
        }.collect { getByUUID(it.getUniqueID()) }
    }

    @Override
    @Nonnull
    RollingStock getByUUIDString(@Nonnull String uuid) {
        return getByUUID(UUID.fromString(uuid))
    }

    @Nonnull
    RollingStock getByUUID(@Nonnull UUID uuid) {
        def entity = host.getAPI(EntityAPI).getByUUID(uuid)
        if (entity.minecraftEntity instanceof ModdedEntity) {
            def moddedEntity = ((ModdedEntity) entity.minecraftEntity).self
            if (moddedEntity instanceof LocomotiveDiesel) {
                return new DieselLocomotiveImpl(entity)
            }
            if (moddedEntity instanceof EntityRollingStock) {
                return new RollingStockImpl(entity)
            }
        }
        throw new ClassCastException()
    }

}
