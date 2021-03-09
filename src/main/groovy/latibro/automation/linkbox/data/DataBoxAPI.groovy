package latibro.automation.linkbox.data

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.tileentity.TileEntityLinkAPI

interface DataBoxAPI extends TileEntityLinkAPI {

    @LuaMethod
    String getData()

    @LuaMethod
    void setData(String data)

}
