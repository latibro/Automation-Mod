package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.lua.LuaObjectProxy
import net.minecraft.world.World
import spock.lang.Specification

class EntityAPIImplTest extends Specification {

    def "LuaMethods"() {
        given:
        def api = Mock(EntityAPI)
        def proxy = new LuaObjectProxy(api)
        when:
        def methodNames = proxy.getMethodNames()
        then:
        methodNames.sort() == ["getByUUID", "linkByUUID", "getFromEntity"].sort()
    }

    // ***** constructor

    def "constructor - null - fails"() {
        when:
        new EntityAPIImpl(null)
        then:
        thrown(NullPointerException)
    }

    def "constructor - world - success"() {
        given:
        def world = Mock(World)
        when:
        def facade = new EntityAPIImpl(world)
        then:
        facade instanceof EntityAPI
    }

    // ***** getLinkedEntity(UUID) -

    def "getLinkedEntity(UUID) - null - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(World))
        when:
        facade.getLinkedEntityFromUUID((UUID) null)
        then:
        thrown(NullPointerException)
    }

    def "getLinkedEntity(UUID) - valid UUID - success"() {
        given:
        def facade = new EntityAPIImpl(Mock(World))
        def uuid = UUID.randomUUID()
        when:
        def entity = facade.getLinkedEntityFromUUID(uuid)
        then:
        entity instanceof LinkedEntity
        entity.getUUID() == uuid
    }

    // ***** getDirectEntity(UUID) -

    def "getDirectEntity(UUID) - null - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(World))
        when:
        facade.getDirectEntityByUUID((UUID) null)
        then:
        thrown(NullPointerException)
    }

    def "getDirectEntity(UUID) - UUID matches one - success"() {
        given:
        def uuid = UUID.randomUUID()
        def minecraftEntity = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> uuid
        })
        def world = Mock(World, {
            getLoadedEntityList() >> [minecraftEntity]
        })
        def facade = new EntityAPIImpl(world)
        when:
        def entity = facade.getDirectEntityByUUID(uuid)
        then:
        entity instanceof DirectEntity
        entity.getUUID() == uuid
    }

    def "getDirectEntity(UUID) - UUID matches multiple - fails"() {
        given:
        def uuid = UUID.randomUUID()
        def minecraftEntity1 = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> uuid
        })
        def minecraftEntity2 = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> uuid
        })
        def world = Mock(World, {
            getLoadedEntityList() >> [minecraftEntity1, minecraftEntity2]
        })
        GroovyMock(DirectEntityImpl, global: true)
        def facade = new EntityAPIImpl(world)
        when:
        facade.getDirectEntityByUUID(uuid)
        then:
        thrown(RuntimeException)
    }

    def "getDirectEntity(UUID) - UUID matches none - fails"() {
        given:
        def uuid = UUID.randomUUID()
        def minecraftEntity = Mock(net.minecraft.entity.Entity, {
            getUniqueID() >> UUID.randomUUID()
        })
        def world = Mock(World, {
            getLoadedEntityList() >> [minecraftEntity]
        })
        GroovyMock(DirectEntityImpl, global: true)
        def facade = new EntityAPIImpl(world)
        when:
        facade.getDirectEntityByUUID(uuid)
        then:
        thrown(RuntimeException)
    }

    // ***** getDirectEntity(Entity) -

    def "getDirectEntity(Entity) - null - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(World))
        when:
        facade.getDirectEntityFromEntity((Entity) null)
        then:
        thrown(NullPointerException)
    }

    def "getDirectEntity(Entity) - DirectEntity - return same"() {
        given:
        def facade = new EntityAPIImpl(Mock(World))
        def input = Mock(DirectEntity)
        when:
        def entity = facade.getDirectEntityFromEntity(input)
        then:
        entity instanceof DirectEntity
        entity == input
    }

    def "getDirectEntity(Entity) - LinkedEntity - return DirectEntity"() {
        given:
        def uuid = UUID.randomUUID()
        def facade = new EntityAPIImpl(Mock(World, {
            getLoadedEntityList() >> [Mock(net.minecraft.entity.Entity, {
                getUniqueID() >> uuid
            })]
        }))
        def input = Mock(LinkedEntity, {
            getUUID() >> uuid
        })
        when:
        def entity = facade.getDirectEntityFromEntity(input)
        then:
        entity instanceof DirectEntity
        entity.getUUID() == input.getUUID()
    }

    def "getDirectEntity(Entity) - LinkedEntity to unknown entity - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(World, {
            getLoadedEntityList() >> []
        }))
        def input = Mock(LinkedEntity, {
            getUUID() >> UUID.randomUUID()
        })
        when:
        facade.getDirectEntityFromEntity(input)
        then:
        thrown(RuntimeException)
    }

    def "getDirectEntity(Entity) - unknown Entity - fails"() {
        given:
        def facade = new EntityAPIImpl(Mock(World))
        def input = Mock(Entity)
        when:
        facade.getDirectEntityFromEntity(input)
        then:
        thrown(ClassCastException)
    }

}
