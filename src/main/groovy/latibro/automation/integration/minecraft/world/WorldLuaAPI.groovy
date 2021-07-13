package latibro.automation.integration.minecraft.world

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.context.Context
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject
import latibro.automation.lua.api.LuaAPI
import net.minecraft.world.World

@LuaObject
class WorldLuaAPI implements LuaAPI {

    protected final Context context

    WorldLuaAPI(Context context) {
        this.context = context
        assert(World)
    }

    private World getWorld() {
        return context.getSource(World)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    String getName() {
        return world.worldInfo.getWorldName()
    }

}
