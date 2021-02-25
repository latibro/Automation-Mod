package latibro.automation

import latibro.automation.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = MODID,
        name = NAME,
        version = VERSION,
        dependencies = "required-after:groovy-libraries@[3.0,);after:opencomputers@[1.7,);after:computercraft@[1.89,);after:immersiverailroading@[1.7,)"
)
class AutomationMod {

    static final String MODID = "automation"
    static final String NAME = "Automation"
    static final String VERSION = "0.2.0"

    @SidedProxy(clientSide = "latibro.automation.proxy.ClientProxy", serverSide = "latibro.automation.proxy.ServerProxy")
    static CommonProxy proxy

    @Mod.Instance
    static AutomationMod instance

    static Logger logger

    @EventHandler
    static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog()
        proxy.preInit(event)
    }

    @EventHandler
    static void init(FMLInitializationEvent event) {
        proxy.init(event)
    }

    @EventHandler
    static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event)
    }

}
