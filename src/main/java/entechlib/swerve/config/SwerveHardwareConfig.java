package entechlib.swerve.config;

import entechlib.swerve.ConfigConstructionUtil.AbsoluteEncoderType;
import entechlib.swerve.ConfigConstructionUtil.ControlType;
import entechlib.swerve.ConfigConstructionUtil.IMUType;

public class SwerveHardwareConfig {
    public final MotorConfig turningMotorConfig = new MotorConfig();
    public final MotorConfig drivingMotorConfig = new MotorConfig();

    public final ModuleConfig frontLeftConfig = new ModuleConfig();
    public final ModuleConfig frontRightConfig = new ModuleConfig();
    public final ModuleConfig rearLeftConfig = new ModuleConfig();
    public final ModuleConfig rearRightConfig = new ModuleConfig();

    private IMUType gyroType;
    private boolean gyroInverted;
    private double gyroOffset;
    private int gyroID;

    private AbsoluteEncoderType encoderType;

    public SwerveHardwareConfig() {
        turningMotorConfig.setControlMethod(ControlType.POSITION);
        drivingMotorConfig.setControlMethod(ControlType.VELOCITY);
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
