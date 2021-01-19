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
        modLanguageAdapter = "com.thesledgehammer.groovyforge.GroovyLanguageAdapter",
        dependencies = "required-after:groovyforge@[1.0,);after:opencomputers@[1.7,);after:computercraft@[1.89,);after:immersiverailroading@[1.7,)"
)
class AutomationMod {

    static final String MODID = "automation"
    static final String NAME = "Automation"
    static final String VERSION = "0.1.0"

    @SidedProxy(clientSide = "latibro.automation.proxy.ClientProxy", serverSide = "latibro.automation.proxy.ServerProxy")
    static CommonProxy proxy

    @Mod.Instance
    static AutomationMod instance

    static Logger logger

    @EventHandler
    void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog()
        proxy.preInit(event)
    }

    @EventHandler
    void init(FMLInitializationEvent event) {
        proxy.init(event)
    }

    @EventHandler
    void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event)
    }

}
