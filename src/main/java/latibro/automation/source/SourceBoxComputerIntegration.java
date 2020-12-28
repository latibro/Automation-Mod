package latibro.automation.source;

import java.util.*;

public class SourceBoxComputerIntegration {

    public Object provider(String name) {
        return new Object();
    }

    public Object test(Object obj) {
        return obj;
    }

    public String javaType(Object obj) {
        return obj.getClass().toString();
    }

    public String javaValue(Object obj) {
        return String.valueOf(obj);
    }

    public Object returnNull() {
        return null;
    }

    public Boolean returnBoolean() {
        return new Boolean(true);
    }

    public Integer returnInteger() {
        return new Integer(123);
    }

    public Double returnDouble() {
        return new Double(123.45);
    }

    public String returnString() {
        return "Hello world";
    }

    public Object[] returnArray() {
        return new Object[] {"Hello world", 123};
    }

    public List returnList() {
        List list = new ArrayList();
        list.add("Hello");
        list.add(123);
        return list;
    }

    public Collection returnCollection() {
        Collection collection = new HashSet();
        collection.add("Hello");
        collection.add(123);
        return collection;
    }

    public Map returnMap() {
        Map map = new HashMap();
        map.put("first", "Hello");
        map.put("second", 123);
        return map;
    }

    public Map returnArrayLikeMap() {
        Map map = new HashMap();
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");
        return map;
    }

}
