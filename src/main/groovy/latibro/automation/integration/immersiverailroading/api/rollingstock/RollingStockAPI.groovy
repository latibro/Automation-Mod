package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI

interface RollingStockAPI extends ContextAPI {

    @LuaMethod
    void setTag(String tag)

    @LuaMethod
    String getTag()

}
