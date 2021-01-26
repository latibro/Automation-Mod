package latibro.automation.core.context.server

import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.collection.LoadedEntityCollectionContextProvider

interface ServerContext extends Context<ServerAPI>, LoadedEntityCollectionContextProvider {

}