package latibro.automation.integration.landofsignals

class SignalAPIImpl implements SignalAPI {

    private final SignalContext context

    SignalAPIImpl(SignalContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    List<String> getStates() {
        return context.getStates()
    }

    @Override
    String getState() {
        return context.getState()
    }

    @Override
    void setState(String state) {
        context.setState(state)
    }

}
