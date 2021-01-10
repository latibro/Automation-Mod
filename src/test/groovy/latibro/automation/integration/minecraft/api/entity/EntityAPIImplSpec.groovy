package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.api.APIHost
import latibro.automation.core.lua.LuaObjectProxy
import net.minecraft.world.World
import spock.lang.Specification

class EntityAPIImplSpec extends Specification {

    def "LuaMethods"() {
        given:
        def api = Mock(EntityAPI)
        def proxy = new LuaObjectProxy(api)
        when:
        def methodNames = proxy.getMethodNames()
        then:
        methodNames.sort() == ["getByUUID", "getAllLoaded", "getAllLoadedAsUUID"].sort()
    }

    // ***** constructor

    def "constructor - null - fails"() {
        when:
        new EntityAPIImpl(null)
        then:
        thrown(NullPointerException)
    }

    def "constructor - world - success"() {
        when:
        def facade = new EntityAPIImpl(Mock(APIHost))
        then:
        facade instanceof EntityAPI
    }

    // ***** linkByUUID(UUID) -

    def "linkByUUID(UUID) - null - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(APIHost))
        when:
        facade.linkByUUID((UUID) null)
        then:
        thrown(NullPointerException)
    }

    def "linkByUUID(UUID) - valid UUID - success"() {
        given:
        def facade = new EntityAPIImpl(Mock(APIHost))
        def uuid = UUID.randomUUID()
        when:
        def entity = facade.linkByUUID(uuid)
        then:
        entity instanceof LinkedEntity
        entity.getUUID() == uuid
    }

    // ***** getByUUID(UUID) -

    def "getByUUID(UUID) - null - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(APIHost))
        when:
        facade.getByUUID((UUID) null)
        then:
        thrown(NullPointerException)
    }

    def "getByUUID(UUID) - UUID matches one - success"() {
        given:
        def uuid = UUID.randomUUID()
        def minecraftEntity = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> uuid
        })
        def host = Mock(APIHost, {
            getMinecraftWorld() >> Mock(World, {
                getLoadedEntityList() >> [minecraftEntity]
            })
        })
        def facade = new EntityAPIImpl(host)
        when:
        def entity = facade.getByUUID(uuid)
        then:
        entity instanceof DirectEntity
        entity.getUUID() == uuid
    }

    def "getByUUID(UUID) - UUID matches multiple - fails"() {
        given:
        def uuid = UUID.randomUUID()
        def minecraftEntity1 = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> uuid
        })
        def minecraftEntity2 = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> uuid
        })
        def host = Mock(APIHost, {
            getMinecraftWorld() >> Mock(World, {
                getLoadedEntityList() >> [minecraftEntity1, minecraftEntity2]
            })
        })
        def facade = new EntityAPIImpl(host)
        when:
        facade.getByUUID(uuid)
        then:
        thrown(RuntimeException)
    }

    def "getByUUID(UUID) - UUID matches none - fails"() {
        given:
        def uuid = UUID.randomUUID()
        def minecraftEntity = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> UUID.randomUUID()
        })
        def host = Mock(APIHost, {
            getMinecraftWorld() >> Mock(World, {
                getLoadedEntityList() >> [minecraftEntity]
            })
        })
        def facade = new EntityAPIImpl(host)
        when:
        facade.getByUUID(uuid)
        then:
        thrown(RuntimeException)
    }

}
