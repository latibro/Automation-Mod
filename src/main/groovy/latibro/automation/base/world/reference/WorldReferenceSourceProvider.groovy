package latibro.automation.base.world.reference

import groovy.transform.CompileStatic
import latibro.automation.base.world.reference.WorldReference
import latibro.automation.core.context.Context
import latibro.automation.core.source.SourceProvider
import net.minecraft.world.World
import net.minecraftforge.common.DimensionManager

@CompileStatic
class WorldReferenceSourceProvider implements SourceProvider {

    @Override
    <T> T getSource(Class<T> type, Context context) {
        if (World.isAssignableFrom(type)) {
            def reference = context.getSource(WorldReference)
            if (reference) {
                def world = DimensionManager.getWorlds().find {
                    it.worldInfo.worldName == reference.worldName
                }
                return (T) world
            }
        }
        return null
    }

}
