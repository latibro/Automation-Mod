package latibro.automation.core.api


import net.minecraft.world.World

interface APIHost {

    def <T extends API> T getAPI(Class<T> apiClass)

    World getMinecraftWorld()

}