package latibro.automation.api.link.location

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.chunk.ChunkLinkAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI

interface LocationLinkAPI extends SingleLinkAPI {

    @LuaMethod
    Number getX()

    @LuaMethod
    Number getY()

    @LuaMethod
    Number getZ()

    @LuaMethod
    WorldLinkAPI getWorld()

    @LuaMethod
    Boolean isLoaded()

    @LuaMethod
    EntityMultiLinkAPI getEntities()

    @LuaMethod
    EntityMultiLinkAPI getEntities(Boolean includeBoundingBoxes)

    @LuaMethod
    Number getDistanceToCoordinates(Number x, Number y, Number z)

    @LuaMethod
    ChunkLinkAPI getChunk()

}