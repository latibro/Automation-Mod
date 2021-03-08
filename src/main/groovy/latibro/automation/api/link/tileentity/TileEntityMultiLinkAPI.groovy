package latibro.automation.api.link.tileentity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.MultiLinkAPI

interface TileEntityMultiLinkAPI extends MultiLinkAPI<TileEntityLinkAPI> {

    @LuaMethod
    TileEntityMultiLinkAPI whereProperty(String property, Object value)

}