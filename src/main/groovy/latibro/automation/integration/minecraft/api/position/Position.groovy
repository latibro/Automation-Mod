package latibro.automation.integration.minecraft.api.position

import latibro.automation.api.core.lua.LuaMethod

interface Position {

    @LuaMethod
    Number getX()

    @LuaMethod
    Number getY()

    @LuaMethod
    Number getZ()

    Map asMap()

}