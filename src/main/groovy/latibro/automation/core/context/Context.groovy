package latibro.automation.core.context

import net.minecraft.world.World

interface Context {

    Context getRootContext()

    World getMinecraftWorld()

}
