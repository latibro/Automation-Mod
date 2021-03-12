package latibro.automation.api.link.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.tileentity.TileEntityMultiLinkAPI

interface EntityLinkAPI extends SingleLinkAPI {

    @LuaMethod
    Boolean isLoaded()

    @LuaMethod
    String getUUID()

    @LuaMethod
    LocationLinkAPI getLocation()

    @LuaMethod
    String getName()

    @LuaMethod
    String getType()

    @LuaMethod
    EntityMultiLinkAPI getNearbyEntities(Number range)

    @LuaMethod
    EntityMultiLinkAPI getNearbyEntities(Number range, Boolean includeBoundingBoxes)

    @LuaMethod
    TileEntityMultiLinkAPI getNearbyTileEntities(Number range)

    @LuaMethod
    TileEntityMultiLinkAPI getNearbyTileEntities(Number range, Boolean includeBoundingBoxes)

}