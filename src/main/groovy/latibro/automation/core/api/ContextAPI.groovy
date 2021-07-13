package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.context.OldContext

interface ContextAPI<T extends OldContext> extends API {

    T getContext()

}