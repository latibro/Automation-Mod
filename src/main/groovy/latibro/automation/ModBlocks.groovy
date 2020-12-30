package latibro.automation

import latibro.automation.interfacebox.InterfaceBoxBlock
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ModBlocks {

    @GameRegistry.ObjectHolder("automation:interface_box")
    static InterfaceBoxBlock interfaceBox

    @SideOnly(Side.CLIENT)
    static void initModels() {
        interfaceBox.initModel()
    }

}
