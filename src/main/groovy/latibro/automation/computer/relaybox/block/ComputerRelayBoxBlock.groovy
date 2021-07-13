package latibro.automation.computer.relaybox.block

import latibro.automation.ModCreativeTabs
import latibro.automation.proxy.ScreenProxy
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

import javax.annotation.Nullable

class ComputerRelayBoxBlock extends Block implements ITileEntityProvider {

    static final ComputerRelayBoxBlock INSTANCE = new ComputerRelayBoxBlock()

    private ComputerRelayBoxBlock() {
        super(Material.IRON)
        setRegistryName("computer-relay-box")
        setUnlocalizedName("automation.computer-relay-box")
        setCreativeTab(ModCreativeTabs.DEFAULT)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    @Nullable
    @Override
    TileEntity createNewTileEntity(World world, int meta) {
        return new ComputerRelayBoxBlockEntity()
    }

    @Override
    boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true
        }

        def blockEntity = world.getTileEntity(pos)
        ScreenProxy.openBlockEntityScreen(blockEntity, player)
        return true
    }

    @Override
    void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity blockEntity = world.getTileEntity(pos)
        IItemHandler itemHandler = blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)
        ItemStack sourceCardItemStack = itemHandler.getStackInSlot(0)
        if (!sourceCardItemStack.isEmpty()) {
            EntityItem sourceCardItemEntity = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), sourceCardItemStack)
            world.spawnEntity(sourceCardItemEntity)
        }
        super.breakBlock(world, pos, state)
    }

}
