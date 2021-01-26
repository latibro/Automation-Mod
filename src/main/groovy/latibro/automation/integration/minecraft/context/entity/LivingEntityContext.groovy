package latibro.automation.integration.minecraft.context.entity


import latibro.automation.core.context.entity.EntityContext

interface LivingEntityContext extends EntityContext {

    boolean navigateTo(double x, double y, double z)

    boolean isAIEnabled()

    void setAIEnabled(boolean enabled)

}