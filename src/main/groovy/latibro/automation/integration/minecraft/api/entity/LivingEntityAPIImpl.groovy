package latibro.automation.integration.minecraft.api.entity

import latibro.automation.integration.minecraft.context.entity.LivingEntityContext

class LivingEntityAPIImpl extends EntityAPIImpl implements LivingEntityAPI {

    LivingEntityAPIImpl(LivingEntityContext context) {
        super(context)
    }

    LivingEntityContext getContext() {
        return super.context as LivingEntityContext
    }

    boolean navigateTo(double x, double y, double z) {
        return getContext().navigateTo(x, y, z)
    }

    @Override
    boolean isAIEnabled() {
        return getContext().isAIEnabled()
    }

    @Override
    void setAIEnabled(boolean enabled) {
        getContext().setAIEnabled(enabled)
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
