package latibro.automation.core.context.world

import latibro.automation.core.context.entity.AbstractEntityCollectionContext
import latibro.automation.core.context.entity.EntityCollectionContext
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.server.AbstractServerContext
import latibro.automation.core.context.server.ServerContext
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World

abstract class AbstractWorldContext implements WorldContext {

    private final def self = this

    @Override
    abstract World getMinecraftWorld()

    @Override
    ServerContext getServerContext() {
        return new AbstractServerContext() {
            @Override
            MinecraftServer getMinecraftServer() {
                return minecraftWorld.minecraftServer
            }
        }
    }

    @Override
    EntityCollectionContext getLoadedEntitiesContext() {
        return new AbstractEntityCollectionContext() {
            @Override
            Collection getAllMinecraftEntity() {
                return minecraftWorld.loadedEntityList
            }
        }
    }

    @Override
    PositionContext getPositionContextByXYZ(double x, double y, double z) {
        Objects.requireNonNull(x)
        Objects.requireNonNull(y)
        Objects.requireNonNull(z)

        return new PositionContext() {
            @Override
            double getX() {
                return x
            }

            @Override
            double getY() {
                return y
            }

            @Override
            double getZ() {
                return z
            }

            @Override
            WorldContext getWorldContext() {
                return self
            }
        }
    }
}