package latibro.automation.core.api.location

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.world.WorldAPI

interface LocationAPI extends ContextAPI {

    @LuaMethod
    boolean isLoaded()

    @LuaMethod
    double getX()

    @LuaMethod
    double getY()

    @LuaMethod
    double getZ()

    @LuaMethod
    WorldAPI getWorld()

    @LuaMethod
    EntityCollectionAPI getEntities()

    @LuaMethod
    double getDistanceToCoordinate(double x, double y, double z)

}