package latibro.automation.core.source.card.item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

abstract class SourceCardItem extends Item {

    abstract Object getSourceObject(ItemStack itemStack)

}
