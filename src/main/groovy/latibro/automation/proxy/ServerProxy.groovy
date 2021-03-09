package latibro.automation.proxy

import groovy.transform.CompileStatic
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.relauncher.Side

@CompileStatic
@Mod.EventBusSubscriber(Side.SERVER)
class ServerProxy extends CommonProxy {

}
