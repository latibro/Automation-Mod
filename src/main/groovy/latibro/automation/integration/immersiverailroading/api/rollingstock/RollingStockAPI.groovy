package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.entity.EntityAPI

interface RollingStockAPI extends EntityAPI {

    @LuaMethod
    void setTag(String tag)

    @LuaMethod
    String getTag()

}
