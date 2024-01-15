package entechlib.swerve.config;

import entechlib.swerve.ConfigConstructionUtil.AbsoluteEncoderType;
import entechlib.swerve.ConfigConstructionUtil.ControlMethod;
import entechlib.swerve.ConfigConstructionUtil.IMUType;
import entechlib.swerve.SwerveHardware;

/**
 * The configuration class used for the {@link SwerveHardware}.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SwerveHardwareConfig {
    /** The configurations for each turning motor. */
    public final MotorConfig turningMotorConfig = new MotorConfig();
    /** The configurations for each drive motor. */
    public final MotorConfig drivingMotorConfig = new MotorConfig();

    /** The configurations for the front left swerve module. */
    public final ModuleConfig frontLeftConfig = new ModuleConfig();
    /** The configurations for the front right swerve module. */
    public final ModuleConfig frontRightConfig = new ModuleConfig();
    /** The configurations for the rear left swerve module. */
    public final ModuleConfig rearLeftConfig = new ModuleConfig();
    /** The configurations for the rear right swerve module. */
    public final ModuleConfig rearRightConfig = new ModuleConfig();

    private IMUType gyroType;
    private boolean gyroInverted;
    private double gyroOffset;
    private int gyroID;

    private AbsoluteEncoderType encoderType;

    /**
     * Sets definite config for the motor configurations.
     */
    public SwerveHardwareConfig() {
        turningMotorConfig.setControlMethod(ControlMethod.POSITION);
        drivingMotorConfig.setControlMethod(ControlMethod.VELOCITY);
    }

    public int getGyroID() {
        return this.gyroID;
    }

    public void setGyroID(int gyroID) {
        this.gyroID = gyroID;
    }

    public IMUType getGyroType() {
        return this.gyroType;
    }

    public void setGyroType(IMUType gyroType) {
        this.gyroType = gyroType;
    }

    public boolean isGyroInverted() {
        return this.gyroInverted;
    }

    public boolean getGyroInverted() {
        return this.gyroInverted;
    }

    public void setGyroInverted(boolean gyroInverted) {
        this.gyroInverted = gyroInverted;
    }

    public double getGyroOffset() {
        return this.gyroOffset;
    }

    public void setGyroOffset(double gyroOffset) {
        this.gyroOffset = gyroOffset;
    }
    

    public AbsoluteEncoderType getEncoderType() {
        return this.encoderType;
    }

    public void setEncoderType(AbsoluteEncoderType encoderType) {
        this.encoderType = encoderType;
    }
}
