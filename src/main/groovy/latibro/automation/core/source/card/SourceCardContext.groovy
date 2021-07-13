package latibro.automation.core.source.card


import latibro.automation.core.context.BaseContext
import latibro.automation.core.source.card.item.SourceCardItem
import net.minecraft.item.ItemStack

class SourceCardContext<E extends SourceCardItem> extends BaseContext {

    private final ItemStack sourceCard

    SourceCardContext(ItemStack sourceCard) {
        //assert(sourceCard.item instanceof E)
        this.sourceCard = sourceCard
    }

    def <T> T getBaseSource(Class<T> type) {
        if (ItemStack.isAssignableFrom(type)) {
            return sourceCard as T
        }
        return null
    }

}
