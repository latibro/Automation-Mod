package latibro.automation

import latibro.automation.linkbox.data.DataBoxBlock
import latibro.automation.linkbox.entity.EntityLinkBoxBlock
import latibro.automation.linkbox.location.LocationLinkBoxBlock
import latibro.automation.linkbox.server.ServerLinkBoxBlock
import latibro.automation.linkbox.world.WorldLinkBoxBlock
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ModBlocks {

    @GameRegistry.ObjectHolder("automation:server_link_box")
    static ServerLinkBoxBlock serverLinkBox

    @GameRegistry.ObjectHolder("automation:world_link_box")
    static WorldLinkBoxBlock worldLinkBox

    @GameRegistry.ObjectHolder("automation:entity_link_box")
    static EntityLinkBoxBlock entityLinkBox

    @GameRegistry.ObjectHolder("automation:location_link_box")
    static LocationLinkBoxBlock locationLinkBox

    @GameRegistry.ObjectHolder("automation:data_box")
    static DataBoxBlock dataBox

    @SideOnly(Side.CLIENT)
    static void initModels() {
        serverLinkBox.initModel()
        worldLinkBox.initModel()
        entityLinkBox.initModel()
        locationLinkBox.initModel()
        dataBox.initModel()
    }

}
