package entechlib.swerve.config;

import entechlib.swerve.ConfigConstructionUtil.ControlType;
import entechlib.swerve.ConfigConstructionUtil.MotorType;

public class MotorConfig {
    private double kP;
    private double kI;
    private double kD;
    private double kFF;
    private int currentLimit;
    private double positionConversionFactor;
    private double velocityConversionFactor;
    private ControlType controlMethod;
    private MotorType motorType;

    public double getP() {
        return this.kP;
    }

    public void setP(double kP) {
        this.kP = kP;
    }

    public double getI() {
        return this.kI;
    }

    public void setI(double kI) {
        this.kI = kI;
    }

    public double getD() {
        return this.kD;
    }

    public void setD(double kD) {
        this.kD = kD;
    }

    public double getFF() {
        return this.kFF;
    }

    public void setFF(double kFF) {
        this.kFF = kFF;
    }

    public int getCurrentLimit() {
        return this.currentLimit;
    }

    public void setCurrentLimit(int currentLimit) {
        this.currentLimit = currentLimit;
    }

    public double getPositionConversionFactor() {
        return this.positionConversionFactor;
    }

    public void setPositionConversionFactor(double positionConversionFactor) {
        this.positionConversionFactor = positionConversionFactor;
    }

    public double getVelocityConversionFactor() {
        return this.velocityConversionFactor;
    }

    public void setVelocityConversionFactor(double velocityConversionFactor) {
        this.velocityConversionFactor = velocityConversionFactor;
    }

    public ControlType getControlMethod() {
        return this.controlMethod;
    }

    public void setControlMethod(ControlType controlMethod) {
        this.controlMethod = controlMethod;
    }

    public MotorType getMotorType() {
        return this.motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }
}
