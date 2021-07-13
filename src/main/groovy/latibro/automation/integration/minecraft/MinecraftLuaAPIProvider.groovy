package latibro.automation.integration.minecraft

import latibro.automation.core.context.Context
import latibro.automation.integration.minecraft.entity.EntityLuaAPI
import latibro.automation.integration.minecraft.world.WorldLuaAPI
import latibro.automation.lua.api.LuaAPI
import latibro.automation.lua.api.LuaAPIProvider

class MinecraftLuaAPIProvider implements LuaAPIProvider {

    @Override
    LuaAPI getLuaAPI(String name, Context context) {
        if (name == "minecraft:world") {
            return new WorldLuaAPI(context)
        }
        if (name == "minecraft:entity") {
            return new EntityLuaAPI(context)
        }
        return null
    }

}
