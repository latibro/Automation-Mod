package latibro.automation.core.context

public abstract class ContextImpl implements Context, ContextProvider {

    private final Map properties;

    protected ContextImpl() {
        this.properties = new HashMap();
    }

    protected ContextImpl(Map properties) {
        this();
        if (properties != null) {
            this.properties.putAll(properties);
        }
    }

    @Override
    public Map getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public boolean hasProperty(Object key) {
        return properties.containsKey(key);
    }

    @Override
    public Object getProperty(Object key) {
        return properties.get(key);
    }

    @Override
    public void setProperty(Object key, Object value) {
        properties.put(key, value);
    }

    @Override
    public void removeProperty(Object key) {

    }

    @Override
    public Context createContext() {
        return new ChildContextImpl(this);
    }

    @Override
    public Context createContext(Map properties) {
        return new ChildContextImpl(this, properties) ;
    }

}
