package entechlib.swerve.config;

public class ImuConfig {
    enum gyroType {
        NAVX
    }

    public final gyroType type;
    public final boolean reversed;

    public ImuConfig(gyroType type, boolean reversed) {
        this.type = type;
        this.reversed = reversed;
    }
}
