package entechlib.swerve.config;

public class ModuleConfigBuilder {
    private final ModuleConfig config;

    /**
     * @param turningMotorID
     * @param drivingMotorID
     * @param absoluteEncoderID If the encoder is plugged into an analog port use
     *                          its port number instead.
     */
    public ModuleConfigBuilder(int turningMotorID, int drivingMotorID, int absoluteEncoderID, double absoluteEncoder) {
        config = new ModuleConfig(turningMotorID, drivingMotorID, absoluteEncoderID, absoluteEncoderID);
    }
}
