package latibro.automation.integration.minecraft.api.position

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.minecraft.api.world.WorldAPI

interface PositionAPI {

    @LuaMethod
    double getX()

    @LuaMethod
    double getY()

    @LuaMethod
    double getZ()

    WorldAPI getWorld()

}