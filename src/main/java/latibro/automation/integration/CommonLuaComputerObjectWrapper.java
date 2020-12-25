package latibro.automation.integration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class CommonLuaComputerObjectWrapper {

    private final Object wrappedObject;

    public CommonLuaComputerObjectWrapper(@Nonnull Object object) {
        this.wrappedObject = Objects.requireNonNull(object);
    }

    @Nonnull
    public Object getWrappedObject() {
        return wrappedObject;
    }

    private Method[] getMethodList() {
        return wrappedObject.getClass().getMethods();
    }

    private Method findMethod(String methodName, Object[] arguments) {
        Stream<Method> matchingMethods = Arrays.stream(getMethodList()).filter(m -> m.getName().equals(methodName));
        //TODO check if multiple methods found
        return matchingMethods.findFirst().get();
    }

    @Nonnull
    public String[] getMethodNames() {
        return Arrays.stream(getMethodList()).map(Method::getName).distinct().toArray(String[]::new);
    }

    private Object callMethod(Method method, Object[] arguments) throws Exception {
        return method.invoke(wrappedObject, arguments);
    }

    @Nullable
    public Object callMethod(@Nonnull String methodName, @Nullable Object[] arguments) throws Exception {
        Method method = findMethod(methodName, arguments);
        return callMethod(method, arguments);
    }

}
