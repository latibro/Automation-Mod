package latibro.automation.core.source

import latibro.automation.core.context.Context

interface SourceProvider {

    def <T> T getSource(Class<T> type, Context context)

}