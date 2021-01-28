package latibro.automation.integration.minecraft

import latibro.automation.core.context.Context
import latibro.automation.core.context.ContextProvider
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.minecraft.context.entity.EntityContextImpl
import latibro.automation.integration.minecraft.context.entity.LivingEntityContextImpl

final class MinecraftContextProvider implements ContextProvider {

    @Override
    List<String> getSubContextNames(Context context) {
        def names = []
        if (context instanceof CoreContext) {
            if (context instanceof EntityContext) {
                names.add("minecraft.entity")
                names.add("minecraft.entity.living")
            }
        }
        return names
    }

    @Override
    Context findSubContext(String name, Context context) {
        if (context instanceof CoreContext) {
            if (context instanceof EntityContext) {
                if (name == "minecraft.entity") {
                    return new EntityContextImpl(context)
                }
                if (name == "minecraft.entity.living") {
                    return new LivingEntityContextImpl(context)
                }
            }
        }
        return null
    }

}