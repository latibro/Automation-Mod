package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface LinkedEntity extends Entity {

    @LuaMethod(
            name = "asLoaded",
            usage = "function() : Entity"
    )
    Entity asLoadedEntity()

}