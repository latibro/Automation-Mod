package latibro.automation.core.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import spock.lang.Specification

class NBTHelperSpec extends Specification {

    def "setItemStackNbt"() {
        given:
        def itemStack = new ItemStack(new Item())
        def nbt = new NBTTagCompound()
        when:
        def pathNbt = NBTHelper.setTag(itemStack, "path", nbt)
        then:
        pathNbt == nbt
    }

}
