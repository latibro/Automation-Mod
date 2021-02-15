package latibro.automation.integration.minecraft

import latibro.automation.core.api.API
import latibro.automation.core.api.APIRegistry
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

    @Override
    boolean hasAPI(Context context) {
        if (context instanceof LivingEntityContext) {
            return true
        }
        if (context instanceof EntityContext) {
            return true
        }
        return super.hasAPI(context)
    }

    @Override
    ContextAPI getAPI(Context context) {
        if (hasAPI(context)) {
            if (context instanceof LivingEntityContext) {
                return new LivingEntityAPIImpl(context)
            }
            if (context instanceof EntityContext) {
                return new EntityAPIImpl(context)
            }
        }
        return super.getAPI(context)
    }

    @Override
    List<String> getAPINames(API api) {
        def names = super.getAPINames(api)
        if (api instanceof CoreEntityAPI) {
            names.add("minecraft:entity")
            names.add("minecraft:entityliving")
        }
        return names
    }

    @Override
    boolean hasAPI(String name, API api) {
        if (api instanceof CoreEntityAPI) {
            if (name == "minecraft:entity") {
                return true
            }
            if (name == "minecraft:entityliving") {
                return true
            }
        }
        return super.hasAPI(name, api)
    }

    @Override
    API getAPI(String name, API api) {
        if (hasAPI(name, api)) {
            if (api instanceof CoreEntityAPI) {
                if (name == "minecraft:entity") {
                    return getAPI(EntityAPI, api)
                }
                if (name == "minecraft:entityliving") {
                    return getAPI(LivingEntityAPI, api)
                }
            }
        }
        return super.getAPI(name, api)
    }

    @Override
    API getAPI(Class<? extends API> cls, API api) {
        if (api instanceof ContextAPI) {
            if (cls == EntityAPI) {
                def context = ContextRegistry.getContext(EntityContext, api.context)
                return APIRegistry.getAPI(context)
            }
            if (cls == LivingEntityAPI) {
                def context = ContextRegistry.getContext(LivingEntityContext, api.context)
                return APIRegistry.getAPI(context)
            }
        }
        return super.getAPI(cls, api)
    }

}