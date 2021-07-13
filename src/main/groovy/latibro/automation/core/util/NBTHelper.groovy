package latibro.automation.core.util

import latibro.automation.AutomationMod
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound

class NBTHelper {

    static Object createCompoundTag(ItemStack itemStack, String key) {
        def nbt = new NBTTagCompound()
        setTag(itemStack, key, nbt)
        return nbt
    }

    static void setTag(ItemStack itemStack, String key, NBTBase nbt) {
        def parentNbt = itemStack.getTagCompound()
        if (!parentNbt) {
            parentNbt = new NBTTagCompound()
            itemStack.setTagCompound(parentNbt)
        }
        setTag(parentNbt, key, nbt)
        AutomationMod.logger.debug("setItemStackTag {} {} {}", parentNbt, key, nbt)
    }

    static void setTag(NBTTagCompound parentNbt, String key, NBTBase nbt) {
        if (!key.contains(".")) {
            parentNbt.setTag(key, nbt)
        } else {
            def pathKey = key.substring(0, key.lastIndexOf("."))
            def pathNbt = getOrCreateCompoundTag(parentNbt, pathKey)
            def entryKey = key.substring(key.lastIndexOf(".") + 1)
            pathNbt.setTag(entryKey, nbt)
        }
        AutomationMod.logger.debug("setTag {} {} {}", parentNbt, key, nbt)
    }

    static NBTTagCompound getOrCreateCompoundTag(ItemStack itemStack, String key) {
        def nbt = (NBTTagCompound) getTag(itemStack, key)
        if (!nbt) {
            nbt = new NBTTagCompound()
            setTag(itemStack, key, nbt)
        }
        return nbt
    }

    static NBTTagCompound getOrCreateCompoundTag(NBTTagCompound parentNbt, String key) {
        def nbt = (NBTTagCompound) getTag(parentNbt, key)
        if (!nbt) {
            nbt = new NBTTagCompound()
            setTag(parentNbt, key, nbt)
        }
        return nbt
    }

    static NBTBase getTag(ItemStack itemStack, String key) {
        def parentNbt = itemStack?.getTagCompound()
        return getTag(parentNbt as NBTTagCompound, key)
    }

    static NBTTagCompound getCompoundTag(ItemStack itemStack, String key) {
        return (NBTTagCompound) getTag(itemStack, key)
    }

    static NBTBase getTag(NBTTagCompound parentNbt, String key) {
        if (!parentNbt) {
            return null
        }
        if (!key.contains(".")) {
            def nbt = parentNbt.getTag(key)
            return nbt
        } else {
            def keySplits = key.split("\\.", 2)
            def firstKey = keySplits[0]
            def restKey = keySplits[1]
            def firstNbt = (NBTTagCompound) getTag(parentNbt, firstKey)
            def nbt = getTag(firstNbt, restKey)
            return nbt
        }
    }

}
