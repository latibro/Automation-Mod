package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI
import latibro.automation.integration.minecraft.api.entity.DirectEntity
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStockAPI

import javax.annotation.Nonnull

class IRRollingStockAPI extends AbstractHostedAPI implements RollingStockAPI {

    IRRollingStockAPI(@Nonnull APIHost host) {
        super(host)
    }

    @Override
    @Nonnull
    IRRollingStock getRollingStockByUUID(@Nonnull String uuid) {
        def entity = new EntityAPIImpl(host).getDirectEntityByUUID(uuid)
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
