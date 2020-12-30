package latibro.automation.core.context

abstract class ContextImpl implements Context, ContextProvider {

    private final Map properties

    protected ContextImpl() {
        this.properties = new HashMap()
    }

    protected ContextImpl(Map properties) {
        this()
        if (properties != null) {
            this.properties.putAll(properties)
        }
    }

    @Override
    Map getProperties() {
        return Collections.unmodifiableMap(properties)
    }

    @Override
    boolean hasProperty(Object key) {
        return properties.containsKey(key)
    }

    @Override
    Object getProperty(Object key) {
        return properties.get(key)
    }

    @Override
    void setProperty(Object key, Object value) {
        properties.put(key, value)
    }

    @Override
    void removeProperty(Object key) {

    }

    @Override
    Context createContext() {
        return new ChildContextImpl(this)
    }

    @Override
    Context createContext(Map properties) {
        return new ChildContextImpl(this, properties)
    }

}
