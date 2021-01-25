package latibro.automation.integration.minecraft.api.entity

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.context.entity.EntityContext
import net.minecraft.entity.EntityLiving

@CompileStatic
class LivingEntityAPIImpl extends EntityAPIImpl implements LivingEntityAPI {

    LivingEntityAPIImpl(EntityContext context) {
        super(context)
    }

    boolean navigateTo(double x, double y, double z) {
        def livingEntity = (EntityLiving) context.minecraftEntity
        def navigator = livingEntity.getNavigator()
        def path = navigator.getPathToXYZ(x, y, z)
        AutomationMod.logger.info("found path: " + path?.getFinalPathPoint()?.toString())
        return navigator.setPath(path, 1)
        //TODO should return some sort of task object, to track the progress of the move
    }

    @Override
    boolean isAIEnabled() {
        def livingEntity = (EntityLiving) context.minecraftEntity
        return !livingEntity.isAIDisabled()
    }

    @Override
    void setAIEnabled(boolean enabled) {
        def livingEntity = (EntityLiving) context.minecraftEntity
        livingEntity.setNoAI(!enabled)
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
