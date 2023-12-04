package entechlib.swerve.config;

public class ModuleConfig {
    public enum motorType {
        sparkMaxNeo
    }

    public enum absoluteEncoderType {
        thrifty;
    }

    public final motorType motorTypes;
    public final int turningMotorID;
    public final int driveMotorID;
    public final int absoluteEncoderID;
    public final absoluteEncoderType absoluteEncoderType;
    public final double encoderOffsetRadians;
    public final boolean drivingMotorInverted;
    public final boolean turningMotorInverted;

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
    public ModuleConfig(motorType motorTypes, absoluteEncoderType absoluteEncoderType, int turningMotorID,
            int driveMotorID, int absoluteEncoderID, double encoderOffsetRadians, boolean drivingMotorInverted,
            boolean turningMotorInverted) {
        this.motorTypes = motorTypes;
        this.turningMotorID = turningMotorID;
        this.driveMotorID = driveMotorID;
        this.absoluteEncoderID = absoluteEncoderID;
        this.absoluteEncoderType = absoluteEncoderType;
        this.drivingMotorInverted = drivingMotorInverted;
        this.turningMotorInverted = turningMotorInverted;
        this.encoderOffsetRadians = encoderOffsetRadians;
    }
}
