package latibro.automation.core.api;

import latibro.automation.core.context.Context;
import latibro.automation.core.context.ContextProvider;
import net.minecraft.world.World;

public abstract class APIImpl {

    private final Context context;

    protected APIImpl(ContextProvider contextProvider) {
        this.context = contextProvider.createContext();
    }

    protected Context getContext() {
        return context;
    }

    protected World getWorld() {
        return getContext().getWorld();
    }

}
