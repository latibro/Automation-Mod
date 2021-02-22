package latibro.automation.core.api.location


import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.context.location.LocationContext

class BaseLocationLinkAPI implements LocationLinkAPI, ContextAPI {

    private final LocationContext context

    BaseLocationLinkAPI(LocationContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    LocationContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    String getLinkType() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    Boolean isLoaded() {
        return context.isLoaded()
    }

    @Override
    Number getX() {
        return context.x
    }

    @Override
    Number getY() {
        return context.y
    }

    @Override
    Number getZ() {
        return context.z
    }

    @Override
    WorldLinkAPI getWorld() {
        return APIRegistry.getAPI(context.worldContext) as WorldLinkAPI
    }

    @Override
    EntityMultiLinkAPI getEntities() {
        return APIRegistry.getAPI(context.getEntities()) as EntityMultiLinkAPI
    }

    @Override
    EntityMultiLinkAPI getEntities(Boolean includeBoundingBoxes) {
        return APIRegistry.getAPI(context.getEntities(includeBoundingBoxes)) as EntityMultiLinkAPI
    }

    @Override
    Number getDistanceToCoordinates(Number x, Number y, Number z) {
        return context.getDistanceToCoordinates(x as int, y as int, z as int)
    }

}
