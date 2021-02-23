package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.context.Context

interface ContextAPI<T extends Context> extends API {

    T getContext()

}