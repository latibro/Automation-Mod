package latibro.automation.integration.landofsignals


import latibro.automation.core.context.AbstractContextProvider
import latibro.automation.core.context.Context
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext

final class LandOfSignalsContextProvider extends AbstractContextProvider {

    @Override
    Context getContext(Class<? extends Context> cls, Context context) {
        if (context instanceof CoreLocationLinkContext) {
            if (cls == SignalContext) {
                return new SignalContext(context)
            }
        }
        return super.getContext(cls, context)
    }

}