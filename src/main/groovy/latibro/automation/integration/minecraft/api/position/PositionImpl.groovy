package latibro.automation.integration.minecraft.api.position

class PositionImpl implements Position {

    private final Number x
    private final Number y
    private final Number z

    PositionImpl(Number x, Number y, Number z) {
        this.x = Objects.requireNonNull(x)
        this.y = Objects.requireNonNull(y)
        this.z = Objects.requireNonNull(z)
    }

    @Override
    Number getX() {
        return x
    }

    @Override
    Number getY() {
        return y
    }

    @Override
    Number getZ() {
        return z
    }

    @Override
    Map<String, Number> asMap() {
        return [
                "x": getX(),
                "y": getY(),
                "z": getZ()
        ]
    }

    static Position valueOf(Map map) {
        assert map.size() == 3
        return new PositionImpl(map.get("x") as Number, map.get("y") as Number, map.get("z") as Number)
    }

}
