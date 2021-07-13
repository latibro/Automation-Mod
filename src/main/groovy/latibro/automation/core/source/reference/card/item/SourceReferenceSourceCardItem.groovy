package latibro.automation.core.source.reference.card.item

import latibro.automation.core.source.card.item.SourceCardItem
import latibro.automation.core.source.reference.SourceReference
import net.minecraft.item.ItemStack

abstract class SourceReferenceSourceCardItem<T extends SourceReference> extends SourceCardItem {

    @Override
    T getSourceObject(ItemStack itemStack) {
        return getSourceReference(itemStack)
    }

    abstract void setSourceReference(ItemStack itemStack, T sourceReference)

    abstract T getSourceReference(ItemStack itemStack)

}
