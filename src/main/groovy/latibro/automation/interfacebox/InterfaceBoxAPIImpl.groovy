package latibro.automation.interfacebox

import groovy.transform.CompileStatic
import latibro.automation.core.api.API
import latibro.automation.core.api.APIHost
import latibro.automation.core.context.tileentity.AbstractTileEntityContext
import latibro.automation.integration.minecraft.api.server.ServerAPIImpl
import latibro.automation.integration.minecraft.api.world.WorldAPIImpl
import net.minecraft.tileentity.TileEntity

@CompileStatic
class InterfaceBoxAPIImpl implements InterfaceBoxAPI, APIHost {

    private final Map<String, API> apis = [:]
    private final InterfaceBoxTileEntity tileEntity

    InterfaceBoxAPIImpl(InterfaceBoxTileEntity tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)

        def context = new AbstractTileEntityContext() {
            @Override
            TileEntity getMinecraftTileEntity() {
                return tileEntity
            }
        }

        apis.put("server", new ServerAPIImpl(context.getWorldContext().getServerContext()))
        apis.put("world", new WorldAPIImpl(context.getWorldContext()))
    }

    @Override
    Set<String> getAllAPIAsNameString() {
        return apis.keySet().asUnmodifiable()
    }

    @Override
    API getAPIByName(String name) {
        def api = apis.get(name)
        if (api) {
            return api
        }
        throw new IllegalArgumentException("Unknown API") //TODO better exception
    }

    @Override
    def <T extends API> T getAPI(Class<T> apiClass) {
        def apis = this.apis.values().findAll {
            apiClass.isAssignableFrom(it.class)
        }
        if (!apis) {
            throw new NullPointerException()
        }
        if (apis.size() > 1) {
            throw new RuntimeException("Multiple found")
        }
        return (T) apis.first()
    }

}
