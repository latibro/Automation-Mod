package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.api.entity.EntityAPIImpl
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.minecraft.context.entity.LivingEntityContext
import latibro.automation.integration.minecraft.context.entity.LivingEntityContextTrait

class LivingEntityAPIImpl extends EntityAPIImpl implements LivingEntityAPI {

    LivingEntityAPIImpl(EntityContext context) {
        super(context)
    }

    protected LivingEntityContext getContext() {
        return Object.context as LivingEntityContextTrait
    }

    boolean navigateTo(double x, double y, double z) {
        return context.navigateTo(x, y, z)
    }

    @Override
    boolean isAIEnabled() {
        return context.isAIEnabled()
    }

    @Override
    void setAIEnabled(boolean enabled) {
        context.setAIEnabled(enabled)
    }

    @Override
    void disableAI() {
        setAIEnabled(false)
    }

    @Override
    void enableAI() {
        setAIEnabled(true)
    }

}
