package latibro.automation.integration.minecraft.context.entity

import groovy.transform.CompileStatic

@CompileStatic
trait LivingEntityContextTrait implements LivingEntityContext {

//    @Override
//    boolean navigateTo(double x, double y, double z) {
//        def livingEntity = (EntityLiving) context.getNativeEntity
//        def navigator = livingEntity.getNavigator()
//        def path = navigator.getPathToXYZ(x, y, z)
//        AutomationMod.logger.info("found path: " + path?.getFinalPathPoint()?.toString())
//        return navigator.setPath(path, 1)
//        //TODO should return some sort of task object, to track the progress of the move
//    }
//
//    @Override
//    boolean isAIEnabled() {
//        def livingEntity = (EntityLiving) context.getNativeEntity
//        return !livingEntity.isAIDisabled()
//    }
//
//    @Override
//    void setAIEnabled(boolean enabled) {
//        def livingEntity = (EntityLiving) context.getNativeEntity
//        livingEntity.setNoAI(!enabled)
//    }

}
