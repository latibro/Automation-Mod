package latibro.automation.computer.relaybox

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlockEntity
import latibro.automation.core.context.Context
import latibro.automation.core.source.card.SourceCardContext
import latibro.automation.core.source.card.SourceCardLuaAPI
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject
import latibro.automation.lua.api.LuaAPI

@LuaObject
class ComputerRelayBoxLuaAPI implements LuaAPI {

    private final Context context

    ComputerRelayBoxLuaAPI(Context context) {
        this.context = context
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    LuaAPI getSource(Number index) {
        def computerRelayBox = context.getSource(ComputerRelayBoxBlockEntity)
        def sourceCard = computerRelayBox.getSourceCard(index as int)
        if (!sourceCard) {
            return null
        }
        def sourceContext = new SourceCardContext(sourceCard)
        return new SourceCardLuaAPI(sourceContext)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    LuaAPI getSourceAsAPI(String apiName) {
        def api = context.getLuaAPI(apiName)
        if (api) {
            return context.getLuaAPI(apiName)
        }
        throw new NullPointerException("API not found")
    }

}