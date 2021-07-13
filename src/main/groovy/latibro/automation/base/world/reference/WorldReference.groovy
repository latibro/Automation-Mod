package latibro.automation.base.world.reference

import latibro.automation.core.source.reference.SourceReference
import net.minecraft.world.World

class WorldReference implements SourceReference {

    // net.minecraftforge.common.DimensionManager.getWorlds() -> WorldInfo.levelName

    private final String worldName

    private WorldReference(String worldName) {
        this.worldName = worldName
    }

    String getWorldName() {
        return worldName
    }

    @Override
    Map asMap() {
        return [
                "name": worldName
        ]
    }

    static WorldReference create(String worldName) {
        return new WorldReference(worldName)
    }

    static WorldReference create(World world) {
        return create(world.worldInfo.worldName)
    }

}
