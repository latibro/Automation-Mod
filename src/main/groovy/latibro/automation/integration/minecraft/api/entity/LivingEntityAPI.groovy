package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface LivingEntityAPI {

    @LuaMethod
    boolean navigateTo(double x, double y, double z)

    @LuaMethod
    boolean isAIEnabled()

    @LuaMethod
    void setAIEnabled(boolean enabled)

    @LuaMethod
    void disableAI()

    @LuaMethod
    void enableAI()

}