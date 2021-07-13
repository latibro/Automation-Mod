package latibro.automation.core.item

import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardItem
import net.minecraft.item.Item
import spock.lang.Specification

class ItemStackSpec extends Specification {

    def "xx"() {
        given:
        def item = EntityReferenceSourceCardItem.INSTANCE
        def untypedItemStack = new UntypedItemStack(item)
        when:
        def typedItemStack = untypedItemStack as TypedItemStack<EntityReferenceSourceCardItem>
        then:
        typedItemStack instanceof UntypedItemStack
        typedItemStack instanceof TypedItemStack<EntityReferenceSourceCardItem>
    }

}

class UntypedItemStack {

    private Item item

    UntypedItemStack(Item item) {
        this.item = item
    }

    Item getItem() {
        return item
    }

}

class TypedItemStack<T extends Item> extends UntypedItemStack {

    TypedItemStack(T item) {
        super(item)
    }

    T getItem() {
        return item
    }

}