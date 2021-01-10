package latibro.automation.core.api

import javax.annotation.Nonnull

abstract class AbstractHostedAPI implements HostedAPI {

    private final APIHost host

    AbstractHostedAPI(@Nonnull APIHost host) {
        this.host = Objects.requireNonNull(host)
    }

    @Override
    @Nonnull
    APIHost getHost() {
        return host
    }

}
