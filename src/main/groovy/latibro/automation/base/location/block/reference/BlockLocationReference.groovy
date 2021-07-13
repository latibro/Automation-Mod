package latibro.automation.base.location.block.reference

import latibro.automation.base.location.reference.LocationReference
import latibro.automation.base.world.reference.WorldReference
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockLocationReference extends LocationReference {

    private final Integer coordinateX
    private final Integer coordinateY
    private final Integer coordinateZ

    private BlockLocationReference(WorldReference worldReference, Integer coordinateX, Integer coordinateY, Integer coordinateZ) {
        super(worldReference)
        this.coordinateX = coordinateX
        this.coordinateY = coordinateY
        this.coordinateZ = coordinateZ
    }

    Integer getCoordinateX() {
        return coordinateX
    }

    Integer getCoordinateY() {
        return coordinateY
    }

    Integer getCoordinateZ() {
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

    static BlockLocationReference create(WorldReference worldReference, Integer coordinateX, Integer coordinateY, Integer coordinateZ) {
        return new BlockLocationReference(worldReference, coordinateX, coordinateY, coordinateZ)
    }

    static BlockLocationReference create(String worldName, Integer coordinateX, Integer coordinateY, Integer coordinateZ) {
        def worldReference = WorldReference.create(worldName)
        return create(worldReference, coordinateX, coordinateY, coordinateZ)
    }

    static BlockLocationReference create(World world, BlockPos coordinates) {
        def worldReference = WorldReference.create(world)
        return create(worldReference, coordinates.x, coordinates.y, coordinates.z)
    }

}
