package latibro.automation;

import latibro.automation.source.SourceBoxBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("automation:source_box")
    public static SourceBoxBlock sourceBox;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        sourceBox.initModel();
    }

}
