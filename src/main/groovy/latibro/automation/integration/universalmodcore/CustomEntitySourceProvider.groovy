package latibro.automation.integration.universalmodcore

import cam72cam.mod.entity.CustomEntity
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.core.context.Context
import latibro.automation.core.source.SourceProvider

class CustomEntitySourceProvider implements SourceProvider {

    @Override
    <T> T getSource(Class<T> type, Context context) {
        if (CustomEntity.isAssignableFrom(type)) {
            def entity = context.getSource(ModdedEntity)
            if (entity) {
                def umcEntity = entity.self
                if (umcEntity) {
                    if (type.isInstance(umcEntity)) {
                        return umcEntity as T
                    }
                }
            }
        }
        return null
    }

}
