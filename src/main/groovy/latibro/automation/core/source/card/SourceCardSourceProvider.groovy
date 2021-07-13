package latibro.automation.core.source.card

import groovy.transform.CompileStatic
import latibro.automation.core.context.Context
import latibro.automation.core.source.Source
import latibro.automation.core.source.SourceProvider
import latibro.automation.core.source.card.item.SourceCardItem
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraftforge.fml.common.FMLCommonHandler

@CompileStatic
class SourceCardSourceProvider implements SourceProvider {

    @Override
    <T> T getSource(Class<T> type, Context context) {
        if (Source.isAssignableFrom(type)) {
            ItemStack itemStack = context.getSource(ItemStack)
            def item = itemStack?.item
            if (item instanceof SourceCardItem) {
                def reference = item.getSourceObject(itemStack)
                return (T) reference
            }
        }
        return null
    }

    private static MinecraftServer getServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance()
    }

}
