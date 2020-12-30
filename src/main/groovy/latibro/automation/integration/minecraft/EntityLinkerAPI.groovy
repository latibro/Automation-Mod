package latibro.automation.integration.minecraft

import latibro.automation.core.api.API
import latibro.automation.core.lua.LuaMethod

public interface EntityLinkerAPI extends API {

    EntityLinkAPI fromUUID(UUID uuid);

    @LuaMethod
    EntityLinkAPI fromUUID(String uuid);

    @LuaMethod
    EntityLinkAPI[] currentLoaded();

}
