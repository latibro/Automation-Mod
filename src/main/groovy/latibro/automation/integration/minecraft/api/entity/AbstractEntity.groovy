package latibro.automation.integration.minecraft.api.entity

abstract class AbstractEntity implements Entity {

    private final EntityAPI host

    AbstractEntity(EntityAPI host) {
        this.host = Objects.requireNonNull(host)
    }

    EntityAPI getHost() {
        return host
    }

    @Override
    String getUUIDString() {
        return getUUID().toString()
    }

}
