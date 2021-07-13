package latibro.automation.core.item

import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

class TypedItemStack<T> {

    static boolean isTyped(ItemStack itemStack, Class type) {
        def item = itemStack.item
        if (item.class.isAssignableFrom(type)) {
            return true
        }
        if (item instanceof ItemBlock) {
            def block = item.block
            if (block.class.isAssignableFrom(type)) {
                return true
            }
        }
        return false
    }

}
