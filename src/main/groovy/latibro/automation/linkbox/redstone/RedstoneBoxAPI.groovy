package latibro.automation.linkbox.redstone

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.tileentity.TileEntityLinkAPI

interface RedstoneBoxAPI extends TileEntityLinkAPI {

    @LuaMethod
    Number getPowerLevel()

    @LuaMethod
    void setPowerLevel(Number power)

}
