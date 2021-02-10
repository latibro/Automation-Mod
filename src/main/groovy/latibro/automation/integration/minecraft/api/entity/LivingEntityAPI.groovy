package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface LivingEntityAPI extends EntityAPI {

    @LuaMethod
    boolean navigateTo(Number x, Number y, Number z)

    @LuaMethod
    boolean isAIEnabled()

    @LuaMethod
    void setAIEnabled(boolean enabled)

    @LuaMethod
    void disableAI()

    @LuaMethod
    void enableAI()

}