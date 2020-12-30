package latibro.automation.interfacebox;

import latibro.automation.core.api.API;
import latibro.automation.core.api.APIImpl;
import latibro.automation.core.context.ContextProvider;
import latibro.automation.core.lua.LuaMethod;
import latibro.automation.integration.minecraft.EntityLinker;

public class InterfaceBox extends APIImpl implements InterfaceBoxAPI {

    public InterfaceBox(ContextProvider contextProvider) {
        super(contextProvider);
    }

    @LuaMethod
    @Override
    public String[] list() {
        return new String[] {"entity_linker"};
    }

    @LuaMethod
    @Override
    public API require(String name) {
        if (name.equals("entity_linker")) {
            return new EntityLinker(getContext());
        } else {
            throw new IllegalArgumentException("Unknown API");
        }
    }

}
