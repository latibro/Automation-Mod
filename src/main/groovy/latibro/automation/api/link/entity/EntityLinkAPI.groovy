package latibro.automation.api.link.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.UnloadableLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI

interface EntityLinkAPI extends SingleLinkAPI, UnloadableLinkAPI {

    @LuaMethod
    String getUUID()

    @LuaMethod
    LocationLinkAPI getLocation()

    @LuaMethod
    String getName()

    @LuaMethod
    String getType()

}