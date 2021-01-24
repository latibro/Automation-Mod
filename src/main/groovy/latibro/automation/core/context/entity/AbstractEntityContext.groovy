package latibro.automation.core.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.server.AbstractServerContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.AbstractWorldContext
import latibro.automation.core.context.world.WorldContext
import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World

@CompileStatic
abstract class AbstractEntityContext<T extends Entity> implements EntityContext<T> {

    private final EntityContext self = this

    abstract T getMinecraftEntity()

    @Override
    WorldContext getWorldContext() {
        return new AbstractWorldContext() {
            @Override
            World getMinecraftWorld() {
                return minecraftEntity.world
            }
        }
    }

    @Override
    ServerContext getServerContext() {
        return new AbstractServerContext() {
            @Override
            MinecraftServer getMinecraftServer() {
                return minecraftEntity.server
            }
        }
    }

    @Override
    PositionContext getPositionContext() {
        return new PositionContext() {
            @Override
            double getX() {
                return minecraftEntity.position.x
            }

            @Override
            double getY() {
                return minecraftEntity.position.y
            }

            @Override
            double getZ() {
                return minecraftEntity.position.z
            }

            @Override
            WorldContext getWorldContext() {
                return self.worldContext
            }
        }
    }

}
