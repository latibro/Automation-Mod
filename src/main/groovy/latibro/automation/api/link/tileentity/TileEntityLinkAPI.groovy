package latibro.automation.api.link.tileentity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI

interface TileEntityLinkAPI extends SingleLinkAPI {

    @LuaMethod
    Boolean isLoaded()

    @LuaMethod
    LocationLinkAPI getLocation()

    @LuaMethod
    String getType()

}