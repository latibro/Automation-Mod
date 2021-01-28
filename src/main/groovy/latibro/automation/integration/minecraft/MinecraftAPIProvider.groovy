package latibro.automation.integration.minecraft

import latibro.automation.core.api.APIProvider
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.context.Context
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import latibro.automation.integration.minecraft.api.entity.LivingEntityAPIImpl
import latibro.automation.integration.minecraft.context.entity.EntityContext
import latibro.automation.integration.minecraft.context.entity.LivingEntityContext

class MinecraftAPIProvider implements APIProvider {

    @Override
    ContextAPI findContextAPI(Context context) {
        if (context instanceof LivingEntityContext) {
            return new LivingEntityAPIImpl(context)
        }
        if (context instanceof EntityContext) {
            return new EntityAPIImpl(context)
        }
        return null
    }

    @Override
    List<String> getFeatureAPINames(Context context) {
        return null
    }

    @Override
    FeatureAPI findFeatureAPI(String name, Context context) {
        return null
    }

}