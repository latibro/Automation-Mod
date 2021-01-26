package latibro.automation.core.context.position

import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.world.WorldContextProvider

interface PositionContext extends Context<PositionAPI>, WorldContextProvider {

    double getX()

    double getY()

    double getZ()

}