package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.entity.EntityLinkAPI

interface RollingStockAPI extends EntityLinkAPI {

    @LuaMethod
    void setTag(String tag)

    @LuaMethod
    String getTag()

}
