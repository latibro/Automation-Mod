package latibro.automation.integration.minecraft

import latibro.automation.core.context.AbstractContextProvider
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.integration.minecraft.context.entity.EntityContext
import latibro.automation.integration.minecraft.context.entity.EntityContextImpl
import latibro.automation.integration.minecraft.context.entity.LivingEntityContext
import latibro.automation.integration.minecraft.context.entity.LivingEntityContextImpl

final class MinecraftContextProvider extends AbstractContextProvider {

    @Override
    Context getContext(Class<? extends Context> cls, Context context) {
        if (context instanceof CoreContext) {
            if (context instanceof latibro.automation.core.context.entity.EntityContext) {
                if (cls == EntityContext) {
                    return new EntityContextImpl(context)
                }
                if (cls == LivingEntityContext) {
                    return new LivingEntityContextImpl(context)
                }
            }
        }
        return super.getContext(cls, context)
    }

}