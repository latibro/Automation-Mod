package latibro.automation

import groovy.transform.CompileStatic
import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlock
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

@CompileStatic
class ModCreativeTabs {

    static final CreativeTabs DEFAULT = new CreativeTabs("automation.default") {
        ItemStack getTabIconItem() {
            return new ItemStack(Item.getItemFromBlock(ComputerRelayBoxBlock.INSTANCE))
        }
    }

}
