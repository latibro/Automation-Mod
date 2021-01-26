package latibro.automation.nativeimpl.context.position

import groovy.transform.CompileStatic
import latibro.automation.core.context.position.AbstractPositionContext

@CompileStatic
abstract class AbstractNativePositionContext extends AbstractPositionContext implements NativePositionContext {

    @Override
    double getX() {
        return nativePosition.x
    }

    @Override
    double getY() {
        return nativePosition.x
    }

    @Override
    double getZ() {
        return nativePosition.x
    }

}
