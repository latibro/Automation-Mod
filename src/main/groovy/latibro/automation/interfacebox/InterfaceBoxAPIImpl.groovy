package latibro.automation.interfacebox

import groovy.transform.CompileStatic
import latibro.automation.core.api.API
import latibro.automation.core.context.tileentity.TileEntityContext

@CompileStatic
class InterfaceBoxAPIImpl implements InterfaceBoxAPI {

    private final Map<String, API> apis = [:]
    private final TileEntityContext context

    InterfaceBoxAPIImpl(TileEntityContext context) {
        this.context = Objects.requireNonNull(context)

        apis.put("server", context.getWorldContext().getServerContext().getAPI())
        apis.put("world", context.getWorldContext().getAPI())
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

}
