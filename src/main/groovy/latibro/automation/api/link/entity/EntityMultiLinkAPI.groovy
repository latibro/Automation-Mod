package latibro.automation.api.link.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.MultiLinkAPI

interface EntityMultiLinkAPI extends MultiLinkAPI<EntityLinkAPI> {

    @LuaMethod
    EntityMultiLinkAPI whereProperty(String property, Object value)

}