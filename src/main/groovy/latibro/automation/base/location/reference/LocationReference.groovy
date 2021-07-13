package latibro.automation.base.location.reference

import latibro.automation.base.world.reference.WorldReference
import latibro.automation.core.source.reference.SourceReference

abstract class LocationReference implements SourceReference {

    private final WorldReference worldReference

    LocationReference(WorldReference worldReference) {
        this.worldReference = Objects.requireNonNull(worldReference)
    }

    WorldReference getWorldReference() {
        return worldReference
    }

    @Override
    Map asMap() {
        return [
                "world": worldReference.asMap()
        ]
    }

}
