package latibro.automation.integration.immersiverailroading.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.context.Context
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject
import latibro.automation.lua.api.LuaAPI

@LuaObject
class RollingStockLuaAPI implements LuaAPI {

    protected final Context context

    RollingStockLuaAPI(Context context) {
        this.context = context
        assert(rollingStock)
    }

    private EntityRollingStock getRollingStock() {
        return context.getSource(EntityRollingStock)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    String getUuid() {
        return rollingStock.getUUID().toString()
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    String getName() {
        return rollingStock.getDefinition().name()
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void setTag(String tag) {
        rollingStock.tag = tag
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    String getTag() {
        return rollingStock.tag
    }

}
