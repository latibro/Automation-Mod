package latibro.automation.integration.minecraft

import latibro.automation.core.api.API
import latibro.automation.core.lua.LuaMethod
import net.minecraft.entity.Entity

public interface EntityLinkAPI extends API {

    UUID getUUID();

    @LuaMethod(name="getUUID")
    String getUUIDString();

    @LuaMethod
    boolean isLoaded();

    Entity getEntity();

}
