package latibro.automation.integration.minecraft.entity


import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.base.location.point.reference.PointLocationReference
import latibro.automation.core.context.Context
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject
import latibro.automation.lua.api.LuaAPI
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList

@LuaObject
class EntityLuaAPI implements LuaAPI {

    protected final Context context

    EntityLuaAPI(Context context) {
        this.context = context
        assert (entity)
    }

    private Entity getEntity() {
        //TODO handle if entity is not loaded/alive
        return context.getSource(Entity)
    }

    @LuaMethod
    //TODO should not have LuaMethod annotation
    @LuaFunction
    String getUuid() {
        return entity.getUniqueID().toString()
    }

    @LuaMethod
    //TODO should not have LuaMethod annotation
    @LuaFunction
    String getName() {
        return entity.getName()
    }

    @LuaMethod
    //TODO should not have LuaMethod annotation
    @LuaFunction
    String getType() {
        return EntityList.getKey(entity)
    }

    @LuaMethod
    //TODO should not have LuaMethod annotation
    @LuaFunction
    Map getLocation() {
        def reference = PointLocationReference.create(entity.world, entity.posX, entity.posY, entity.posZ)
        return reference.asMap()
    }

}
