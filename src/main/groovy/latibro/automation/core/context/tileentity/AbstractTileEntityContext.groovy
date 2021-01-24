package latibro.automation.core.context.tileentity

import groovy.transform.CompileStatic
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.world.AbstractWorldContext
import latibro.automation.core.context.world.WorldContext
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

@CompileStatic
abstract class AbstractTileEntityContext<T extends TileEntity> implements TileEntityContext<T> {

    private final TileEntityContext self = this

    abstract T getMinecraftTileEntity()

    @Override
    WorldContext getWorldContext() {
        return new AbstractWorldContext() {
            @Override
            World getMinecraftWorld() {
                return minecraftTileEntity.world
            }
        }
    }

    @Override
    PositionContext getPositionContext() {
        return new PositionContext() {
            @Override
            double getX() {
                return minecraftTileEntity.pos.x
            }

            @Override
            double getY() {
                return minecraftTileEntity.pos.y
            }

            @Override
            double getZ() {
                return minecraftTileEntity.pos.z
            }

            @Override
            WorldContext getWorldContext() {
                return self.worldContext
            }
        }
    }

}
