package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardContainer
import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardItem
import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardScreen
import latibro.automation.base.location.block.reference.card.BlockLocationReferenceSourceCardContainer
import latibro.automation.base.location.block.reference.card.BlockLocationReferenceSourceCardItem
import latibro.automation.base.location.block.reference.card.BlockLocationReferenceSourceCardScreen
import latibro.automation.base.world.reference.card.WorldReferenceSourceCardContainer
import latibro.automation.base.world.reference.card.WorldReferenceSourceCardItem
import latibro.automation.base.world.reference.card.WorldReferenceSourceCardScreen
import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlockEntity
import latibro.automation.computer.relaybox.block.ComputerRelayBoxContainer
import latibro.automation.computer.relaybox.block.ComputerRelayBoxScreen
import latibro.automation.linkbox.data.DataBoxContainer
import latibro.automation.linkbox.data.DataBoxScreen
import latibro.automation.linkbox.data.DataBoxTileEntity
import latibro.automation.linkbox.redstone.RedstoneBoxContainer
import latibro.automation.linkbox.redstone.RedstoneBoxScreen
import latibro.automation.linkbox.redstone.RedstoneBoxTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

@CompileStatic
class ScreenProxy implements IGuiHandler {

    static final int BLOCK_ENTITY_SCREEN = 1
    static final int HELD_ITEM_SCREEN = 2

    static void openBlockEntityScreen(TileEntity blockEntity, EntityPlayer player) {
        def world = blockEntity.world
        def pos = blockEntity.pos
        player.openGui(AutomationMod.instance, ScreenProxy.BLOCK_ENTITY_SCREEN, world, pos.getX(), pos.getY(), pos.getZ())
    }

    static void openHeldItemScreen(EntityPlayer player) {
        player.openGui(AutomationMod.instance, ScreenProxy.HELD_ITEM_SCREEN, null, 0, 0, 0)
    }

    @Override
    Object getServerGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        if (screenId == BLOCK_ENTITY_SCREEN) {
            def pos = new BlockPos(x, y, z)
            def blockEntity = world.getTileEntity(pos)

            if (blockEntity instanceof DataBoxTileEntity) {
                return new DataBoxContainer(blockEntity)
            }
            if (blockEntity instanceof RedstoneBoxTileEntity) {
                return new RedstoneBoxContainer(blockEntity)
            }
            if (blockEntity instanceof ComputerRelayBoxBlockEntity) {
                return new ComputerRelayBoxContainer(blockEntity, player)
            }
        }
        if (screenId == HELD_ITEM_SCREEN) {
            def itemStack = player.getHeldItem(EnumHand.MAIN_HAND)
            def item = itemStack.item

            if (item instanceof WorldReferenceSourceCardItem) {
                return new WorldReferenceSourceCardContainer(player.inventoryContainer, itemStack)
            }
            if (item instanceof BlockLocationReferenceSourceCardItem) {
                return new BlockLocationReferenceSourceCardContainer(player.inventoryContainer, itemStack)
            }
            if (item instanceof EntityReferenceSourceCardItem) {
                return new EntityReferenceSourceCardContainer(player.inventoryContainer, itemStack)
            }
        }
        return null
    }

    @Override
    Object getClientGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        def container = getServerGuiElement(screenId, player, world, x, y, z)

        if (container instanceof WorldReferenceSourceCardContainer) {
            return new WorldReferenceSourceCardScreen(container)
        }
        if (container instanceof EntityReferenceSourceCardContainer) {
            return new EntityReferenceSourceCardScreen(container)
        }
        if (container instanceof BlockLocationReferenceSourceCardContainer) {
            return new BlockLocationReferenceSourceCardScreen(container)
        }
        if (container instanceof DataBoxContainer) {
            return new DataBoxScreen(container)
        }
        if (container instanceof RedstoneBoxContainer) {
            return new RedstoneBoxScreen(container)
        }
        if (container instanceof ComputerRelayBoxContainer) {
            return new ComputerRelayBoxScreen(container)
        }
        return null
    }

}
