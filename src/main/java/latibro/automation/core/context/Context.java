package latibro.automation.core.context;

import net.minecraft.world.World;

import java.util.Map;

public interface Context extends ContextProvider {

    Map getProperties();

    boolean hasProperty(Object key);

    Object getProperty(Object key);

    void setProperty(Object key, Object value);

    void removeProperty(Object key);

    World getWorld();

}
