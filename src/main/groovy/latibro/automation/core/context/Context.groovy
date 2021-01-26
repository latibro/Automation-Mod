package latibro.automation.core.context

import latibro.automation.core.api.API

interface Context<T extends API> {

    T getAPI()

}
