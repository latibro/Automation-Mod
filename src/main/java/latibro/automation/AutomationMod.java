package latibro.automation;

import latibro.automation.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AutomationMod.MODID, name = AutomationMod.NAME, version = AutomationMod.VERSION)
public class AutomationMod {

    public static final String MODID = "automation";
    public static final String NAME = "Automation";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "latibro.automation.proxy.ClientProxy", serverSide = "latibro.automation.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static AutomationMod instance;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
