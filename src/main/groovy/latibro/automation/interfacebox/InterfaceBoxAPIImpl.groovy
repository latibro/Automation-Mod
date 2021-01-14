package latibro.automation.interfacebox

import groovy.transform.CompileStatic
import latibro.automation.core.api.API
import latibro.automation.core.api.APIHost
import latibro.automation.core.api.HostedAPI
import latibro.automation.integration.immersiverailroading.api.rollingstock.RollingStockAPIImpl
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import net.minecraft.world.World
import net.minecraftforge.fml.common.Loader

@CompileStatic
class InterfaceBoxAPIImpl implements InterfaceBoxAPI, APIHost {

    private final Map<String, HostedAPI> apis = [:]
    private final InterfaceBoxTileEntity tileEntity

    InterfaceBoxAPIImpl(InterfaceBoxTileEntity tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)

        apis.put("entity", new EntityAPIImpl(this))

        if (Loader.isModLoaded("immersiverailroading")) {
            apis.put("immersive_railroading.rolling_stock", new RollingStockAPIImpl(this))
        }
    }

    @Override
    Set<String> getAllAPIAsNameString() {
        return apis.keySet().asUnmodifiable()
    }

    @Override
    HostedAPI getAPIByName(String name) {
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

    @Override
    World getMinecraftWorld() {
        return tileEntity.getWorld()
    }

}
