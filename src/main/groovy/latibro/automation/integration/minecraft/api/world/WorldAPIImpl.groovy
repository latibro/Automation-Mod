package latibro.automation.integration.minecraft.api.world


import latibro.automation.core.context.world.WorldContext
import latibro.automation.integration.minecraft.api.entity.EntityCollectionAPI
import latibro.automation.integration.minecraft.api.entity.EntityCollectionAPIImpl
import latibro.automation.integration.minecraft.api.position.PositionAPI
import latibro.automation.integration.minecraft.api.position.PositionAPIImpl
import latibro.automation.integration.minecraft.api.server.ServerAPI
import latibro.automation.integration.minecraft.api.server.ServerAPIImpl

class WorldAPIImpl implements WorldAPI {

    private final WorldContext context

    WorldAPIImpl(WorldContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    ServerAPI getServer() {
        return new ServerAPIImpl(context.getServerContext())
    }

    @Override
    EntityCollectionAPI getAllLoadedEntity() {
        return new EntityCollectionAPIImpl(context.loadedEntitiesContext)
    }

    @Override
    PositionAPI getPositionByXYZ(double x, double y, double z) {
        return new PositionAPIImpl(context.getPositionContextByXYZ(x, y, z))
    }

}
