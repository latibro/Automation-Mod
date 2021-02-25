package latibro.automation.integration.landofsignals

import latibro.automation.core.context.Context
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import net.landofrails.landofsignals.blocks.BlockSignalPart
import net.landofrails.landofsignals.tile.TileSignalPart

class SignalContext implements Context {

    private final CoreLocationLinkContext locationLink

    SignalContext(CoreLocationLinkContext locationLink) {
        this.locationLink = Objects.requireNonNull(locationLink)
    }

    List<String> getStates() {
        def te = locationLink.world.nativeWorld.getTileEntity(locationLink.nativeLocation)
        return BlockSignalPart.getStates()
    }

    String getState() {
        return TileSignalPart.getTexturePath()
    }

    void setState(String state) {
        TileSignalPart.setTexturePath(state)
    }

}
