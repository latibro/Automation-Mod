package latibro.automation.base.entity.reference

import groovy.transform.CompileStatic
import latibro.automation.base.entity.reference.EntityReference
import latibro.automation.core.context.Context
import latibro.automation.core.source.SourceProvider
import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraftforge.fml.common.FMLCommonHandler

@CompileStatic
class EntityReferenceSourceProvider implements SourceProvider {

    @Override
    <T> T getSource(Class<T> type, Context context) {
        if (Entity.isAssignableFrom(type)) {
            EntityReference entityRef = context.getSource(EntityReference)
            if (entityRef) {
                Entity entity = server.getEntityFromUuid(entityRef.entityUuid)
                return (T) entity
            }
        }
        return null
    }

    private static MinecraftServer getServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance()
    }

}
