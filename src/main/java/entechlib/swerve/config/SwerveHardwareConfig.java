package entechlib.swerve.config;

import entechlib.swerve.ConfigConstructionUtil.AbsoluteEncoderType;
import entechlib.swerve.ConfigConstructionUtil.IMUType;

public class SwerveHardwareConfig {
    private MotorConfig turningMotor = new MotorConfig();
    private MotorConfig drivingMotor = new MotorConfig();

    private ModuleConfig frontLeft = new ModuleConfig();
    private ModuleConfig frontRight = new ModuleConfig();
    private ModuleConfig rearLeft = new ModuleConfig();
    private ModuleConfig rearRight = new ModuleConfig();

    private IMUType gyroType;
    private boolean gyroInverted;
    private double gyroOffset;
    private int gyroID;

    private AbsoluteEncoderType encoderType;

    public int getGyroID() {
        return this.gyroID;
    }

    public void setGyroID(int gyroID) {
        this.gyroID = gyroID;
    }

    public MotorConfig getTurningMotorConfig() {
        return this.turningMotor;
    }

    public void setTurningMotorConfig(MotorConfig turningMotor) {
        this.turningMotor = turningMotor;
    }

    public MotorConfig getDrivingMotorConfig() {
        return this.drivingMotor;
    }

    public void setDrivingMotorConfig(MotorConfig drivingMotor) {
        this.drivingMotor = drivingMotor;
    }

    public ModuleConfig getFrontLeft() {
        return this.frontLeft;
    }

    public void setFrontLeft(ModuleConfig frontLeft) {
        this.frontLeft = frontLeft;
    }

    public ModuleConfig getFrontRight() {
        return this.frontRight;
    }

    public void setFrontRight(ModuleConfig frontRight) {
        this.frontRight = frontRight;
    }

    public ModuleConfig getRearLeft() {
        return this.rearLeft;
    }

    public void setRearLeft(ModuleConfig rearLeft) {
        this.rearLeft = rearLeft;
    }

    public ModuleConfig getRearRight() {
        return this.rearRight;
    }

    public void setRearRight(ModuleConfig rearRight) {
        this.rearRight = rearRight;
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
