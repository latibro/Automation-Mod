package latibro.automation.nativeimpl.context.position

import groovy.transform.CompileStatic

@CompileStatic
abstract class AbstractNativePositionContext implements NativePositionContext {

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
