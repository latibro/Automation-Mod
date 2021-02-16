package latibro.automation.integration.minecraft

import latibro.automation.core.api.API
import latibro.automation.core.api.AbstractAPIProvider
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.CoreEntityAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.ContextRegistry
import latibro.automation.integration.minecraft.api.entity.EntityAPI
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import latibro.automation.integration.minecraft.api.entity.LivingEntityAPI
import latibro.automation.integration.minecraft.api.entity.LivingEntityAPIImpl
import latibro.automation.integration.minecraft.context.entity.EntityContext
import latibro.automation.integration.minecraft.context.entity.LivingEntityContext 

class MinecraftAPIProvider extends AbstractAPIProvider {

    private static final def API_NAME_MAP = [
            "minecraft:entity" : EntityAPI,
            "minecraft:entityliving" : LivingEntityAPI
    ]

    @Override
    API getAPI(Context context) {
        if (context instanceof LivingEntityContext) {
            return getAPI(LivingEntityAPI, context)
        }
        if (context instanceof EntityContext) {
            return getAPI(EntityAPI, context)
        }
        return super.getAPI(context)
    }

    @Override
    API getAPI(Class<? extends API> cls, Context context) {
        if (cls == LivingEntityAPI) {
            if (context instanceof LivingEntityContext) {
                return new LivingEntityAPIImpl(context)
            }
            if (ContextRegistry.hasContext(EntityContext, context)) {
                return getAPI(cls, ContextRegistry.getContext(LivingEntityContext, context))
            }
        }
        if (cls == EntityAPI) {
            if (context instanceof EntityContext) {
                return new EntityAPIImpl(context)
            }
            if (ContextRegistry.hasContext(EntityContext, context)) {
                return getAPI(cls, ContextRegistry.getContext(EntityContext, context))
            }
        }
        return super.getAPI(cls, context)
    }

    @Override
    API getAPI(Class<? extends API> cls, API contextApi) {
        if (contextApi instanceof ContextAPI) {
            return getAPI(cls, contextApi.context)
        }
        return super.getAPI(cls, contextApi)
    }

    @Override
    List<String> getAPINames(API contextApi) {
        def names = super.getAPINames(contextApi)
        if (contextApi instanceof CoreEntityAPI) {
            names.add("minecraft:entity")
            names.add("minecraft:entityliving")
        }
        return names
    }

    @Override
    boolean hasAPI(String name, API contextApi) {
        if (contextApi instanceof CoreEntityAPI) {
            if (name == "minecraft:entity") {
                return true
            }
            if (name == "minecraft:entityliving") {
                return true
            }
        }
        return super.hasAPI(name, contextApi)
    }

    @Override
    API getAPI(String name, API contextApi) {
        if (hasAPI(name, contextApi)) {
            if (contextApi instanceof CoreEntityAPI) {
                if (name == "minecraft:entity") {
                    return getAPI(EntityAPI, contextApi)
                }
                if (name == "minecraft:entityliving") {
                    return getAPI(LivingEntityAPI, contextApi)
                }
            }
        }
        return super.getAPI(name, contextApi)
    }

}