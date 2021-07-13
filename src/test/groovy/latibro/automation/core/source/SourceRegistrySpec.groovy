package latibro.automation.core.source

import latibro.automation.core.context.Context
import spock.lang.Specification

class SourceRegistrySpec extends Specification {

    def "finds one source"() {
        given:
        def sourceRegistry = new SourceRegistry()
        def source = new Object()
        sourceRegistry.register(new SourceProvider() {
            @Override
            <T> List<T> getSource(Class<T> type, Context context) {
                return [source] as List<T>
            }
        })
        when:
        def sources = sourceRegistry.findSources(null, null)
        then:
        sources == [source]
    }

    def "finds multiple sources from same provider"() {
        given:
        def sourceRegistry = new SourceRegistry()
        def sourceA = new Object()
        def sourceB = new Object()
        sourceRegistry.register(new SourceProvider() {
            @Override
            <T> List<T> getSource(Class<T> type, Context context) {
                return [sourceA, sourceB] as List<T>
            }
        })
        when:
        def sources = sourceRegistry.findSources(null, null)
        then:
        sources == [sourceA, sourceB]
    }

    def "finds multiple sources from different providers"() {
        given:
        def sourceRegistry = new SourceRegistry()
        def sourceA = new Object()
        def sourceB = new Object()
        sourceRegistry.register(new SourceProvider() {
            @Override
            <T> List<T> getSource(Class<T> type, Context context) {
                return [sourceA] as List<T>
            }
        })
        sourceRegistry.register(new SourceProvider() {
            @Override
            <T> List<T> getSource(Class<T> type, Context context) {
                return [sourceB] as List<T>
            }
        })
        when:
        def sources = sourceRegistry.findSources(null, null)
        then:
        sources == [sourceA, sourceB]
    }

    def "removes dublicates"() {
        given:
        def sourceRegistry = new SourceRegistry()
        def source = new Object()
        sourceRegistry.register(new SourceProvider() {
            @Override
            <T> List<T> getSource(Class<T> type, Context context) {
                return [source] as List<T>
            }
        })
        sourceRegistry.register(new SourceProvider() {
            @Override
            <T> List<T> getSource(Class<T> type, Context context) {
                return [source] as List<T>
            }
        })
        when:
        def sources = sourceRegistry.findSources(null, null)
        then:
        sources == [source]
    }

}
