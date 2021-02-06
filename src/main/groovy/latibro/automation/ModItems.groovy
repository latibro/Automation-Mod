package latibro.automation


import latibro.automation.linkbox.entity.EntityLinkCardItem
import latibro.automation.linkbox.location.LocationLinkCardItem
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ModItems {

    @GameRegistry.ObjectHolder("automation:entity_link_card")
    static EntityLinkCardItem entityLinkCard

    @GameRegistry.ObjectHolder("automation:location_link_card")
    static LocationLinkCardItem locationLinkCard

    @SideOnly(Side.CLIENT)
    static void initModels() {
        entityLinkCard.initModel()
        locationLinkCard.initModel()
    }

}
