package latibro.automation.integration.computercraft;

import latibro.automation.integration.CommonLuaComputerObjectConverter;
import latibro.automation.integration.CommonLuaComputerObjectWrapper;

public class ComputerCraftObjectConverter extends CommonLuaComputerObjectConverter {

    @Override
    public Object wrapObject(Object object) {
        Object wrappedObject = super.wrapObject(object);
        if (wrappedObject instanceof CommonLuaComputerObjectWrapper) {
            return new ComputerCraftWrappedObject((CommonLuaComputerObjectWrapper) wrappedObject);
        } else {
            return wrappedObject;
        }
    }

    public Object unwrapObject(Object object) {
        Object unwrappedObject;
        if (object instanceof ComputerCraftWrappedObject) {
            unwrappedObject = ((ComputerCraftWrappedObject) object).getWrappedObject();
        } else {
            unwrappedObject = object;
        }
        return super.unwrapObject(unwrappedObject);
    }

}
