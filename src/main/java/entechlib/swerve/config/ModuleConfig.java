package entechlib.swerve.config;

public class ModuleConfig {
    public final int turningMotorID;
    public final int driveMotorID;
    public final int absoluteEncoderID;
    public final double encoderOffsetRadians;
    public boolean drivingMotorInverted = false;
    public boolean turningMotorInverted = true;

    /**
     * 
     * 
     * @param motorTypes
     * @param absoluteEncoderType
     * @param turningMotorID
     * @param driveMotorID
     * @param absoluteEncoderID    use the analog port instead of canbus id if
     *                             needed.
     * @param drivingMotorInverted
     * @param turningMotorInverted
     */
    public ModuleConfig(int turningMotorID,
            int driveMotorID, int absoluteEncoderID, double encoderOffsetRadians) {
        this.turningMotorID = turningMotorID;
        this.driveMotorID = driveMotorID;
        this.absoluteEncoderID = absoluteEncoderID;
        this.encoderOffsetRadians = encoderOffsetRadians;
    }
}
