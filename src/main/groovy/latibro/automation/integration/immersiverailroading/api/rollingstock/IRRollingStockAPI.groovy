package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import cam72cam.mod.entity.ModdedEntity
import groovy.transform.CompileStatic
import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI
import latibro.automation.integration.minecraft.api.entity.DirectEntity
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStockAPI

import javax.annotation.Nonnull

@CompileStatic
class IRRollingStockAPI extends AbstractHostedAPI implements RollingStockAPI {

    IRRollingStockAPI(@Nonnull APIHost host) {
        super(host)
    }

    @Override
    List<String> getAllLoadedAsUUIDString() {
        return getAllLoadedAsUUID().collect { it.toString() }
    }

    @Override
    List<UUID> getAllLoadedAsUUID() {
        return getAllLoaded().collect { it.getUUID() }
    }

    @Override
    List<IRRollingStock> getAllLoaded() {
        return host.minecraftWorld.loadedEntityList.findAll {
            return it instanceof ModdedEntity && it.self instanceof EntityRollingStock
        }.collect { getByUUID(it.getUniqueID()) }
    }

    @Override
    @Nonnull
    IRRollingStock getByUUIDString(@Nonnull String uuid) {
        return getByUUID(UUID.fromString(uuid))
    }

    @Nonnull
    IRRollingStock getByUUID(@Nonnull UUID uuid) {
        def entity = new EntityAPIImpl(host).getByUUID(uuid)
        if (entity instanceof DirectEntity) {
            if (entity.minecraftEntity instanceof ModdedEntity) {
                def moddedEntity = ((ModdedEntity) entity.minecraftEntity).self
                if (moddedEntity instanceof LocomotiveDiesel) {
                    return new IRDieselLocomotive(entity)
                }
                if (moddedEntity instanceof EntityRollingStock) {
                    return new IRRollingStock(entity)
                }
            }
        }
        throw new ClassCastException()
    }

}
