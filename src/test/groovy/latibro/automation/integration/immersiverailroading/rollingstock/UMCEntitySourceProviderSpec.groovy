package latibro.automation.integration.immersiverailroading.rollingstock

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.mod.entity.CustomEntity
import spock.lang.Specification

class UMCEntitySourceProviderSpec extends Specification {

    def "x"() {
        expect:
        CustomEntity.isAssignableFrom(Locomotive)
    }

}
