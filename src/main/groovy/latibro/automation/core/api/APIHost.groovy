package latibro.automation.core.api

interface APIHost {

    def <T extends API> T getAPI(Class<T> apiClass)

}