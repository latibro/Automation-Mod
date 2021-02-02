package latibro.automation.integration.minecraft.context.entity

import groovy.transform.CompileStatic
import net.minecraft.entity.EntityLiving

@CompileStatic
class LivingEntityContextImpl extends EntityContextImpl<EntityLiving> implements LivingEntityContext {

    LivingEntityContextImpl(latibro.automation.core.context.entity.EntityContext entityContext) {
        super(entityContext)
    }

    @Override
    boolean navigateTo(double x, double y, double z) {
        def navigator = minecraftEntity.getNavigator()
        def path = navigator.getPathToXYZ(x, y, z)
        return navigator.setPath(path, 1)
    }

    @Override
    boolean isAIEnabled() {
        return !minecraftEntity.isAIDisabled()
    }

    @Override
    void setAIEnabled(boolean enabled) {
        minecraftEntity.setNoAI(!enabled)
    }

}
