package latibro.automation.interfacebox

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.context.tileentity.TileEntityContext
import latibro.automation.nativeimpl.context.tileentity.AbstractNativeTileEntityContext
import net.minecraft.tileentity.TileEntity

@CompileStatic
class InterfaceBoxAPIImpl implements InterfaceBoxAPI {

    private final TileEntityContext context

    InterfaceBoxAPIImpl(InterfaceBoxTileEntity tileEntity) {
        Objects.requireNonNull(tileEntity)

        //TODO maybe this should extend some RootContext interface
        this.context = new AbstractNativeTileEntityContext() {
            @Override
            TileEntity getNativeTileEntity() {
                return tileEntity
            }
        }
    }

    @Override
    LocationAPI getLocation() {
        return (LocationAPI) APIRegistry.getContextAPI(context.locationContext)
    }

    @Override
    WorldAPI getWorld() {
        return (WorldAPI) APIRegistry.getContextAPI(context.worldContext)
    }

    @Override
    ServerAPI getServer() {
        return (ServerAPI) APIRegistry.getContextAPI(context.worldContext.serverContext)
    }

    @Override
    String[] getAPINames() {
        //TODO maybe this should be something like getRootAPINames()
        return APIRegistry.getFeatureAPINames(context) as String[]
    }

    @Override
    FeatureAPI getAPI(String name) {
        //TODO maybe this should be something like getRootAPI()
        def api = APIRegistry.getFeatureAPI(name, context)
        if (api) {
            return api
        }
        throw new IllegalArgumentException("Unknown API") //TODO better exception
    }

}
