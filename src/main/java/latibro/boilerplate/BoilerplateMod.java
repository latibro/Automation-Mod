package latibro.boilerplate;

import latibro.boilerplate.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BoilerplateMod.MODID, name = BoilerplateMod.NAME, version = BoilerplateMod.VERSION)
public class BoilerplateMod {

    public static final String MODID = "boilerplate";
    public static final String NAME = "Boilerplate";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "latibro.boilerplate.proxy.ClientProxy", serverSide = "latibro.boilerplate.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static BoilerplateMod instance;

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
