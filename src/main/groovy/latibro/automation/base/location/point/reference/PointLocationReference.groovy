package latibro.automation.base.location.point.reference

import latibro.automation.base.location.reference.LocationReference
import latibro.automation.base.world.reference.WorldReference
import net.minecraft.world.World

class PointLocationReference extends LocationReference {

    private final Double coordinateX
    private final Double coordinateY
    private final Double coordinateZ

    private PointLocationReference(WorldReference worldReference, Double coordinateX, Double coordinateY, Double coordinateZ) {
        super(worldReference)
        this.coordinateX = coordinateX
        this.coordinateY = coordinateY
        this.coordinateZ = coordinateZ
    }

    Double getCoordinateX() {
        return coordinateX
    }

    Double getCoordinateY() {
        return coordinateY
    }

    Double getCoordinateZ() {
        return coordinateZ
    }

    @Override
    Map asMap() {
        return super.asMap() + [
                "coordinates": [
                        "x": coordinateX,
                        "y": coordinateY,
                        "z": coordinateZ
                ]
        ]
    }

    static PointLocationReference create(WorldReference worldReference, Double coordinateX, Double coordinateY, Double coordinateZ) {
        return new PointLocationReference(worldReference, coordinateX, coordinateY, coordinateZ)
    }

    static PointLocationReference create(String worldName, Double coordinateX, Double coordinateY, Double coordinateZ) {
        def worldReference = WorldReference.create(worldName)
        return create(worldReference, coordinateX, coordinateY, coordinateZ)
    }

    static PointLocationReference create(World world, Double coordinateX, Double coordinateY, Double coordinateZ) {
        def worldReference = WorldReference.create(world)
        return create(worldReference, coordinateX, coordinateY, coordinateZ)
    }

}
