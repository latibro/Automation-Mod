package latibro.automation.integration.minecraft.api.entity

import latibro.automation.integration.minecraft.context.entity.LivingEntityContext

class LivingEntityAPIImpl extends EntityAPIImpl implements LivingEntityAPI {

    LivingEntityAPIImpl(LivingEntityContext context) {
        super(context)
    }

    LivingEntityContext getContext() {
        return super.context as LivingEntityContext
    }

    boolean navigateTo(Number x, Number y, Number z) {
        return getContext().navigateTo(x as double, y as double, z as double)
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
