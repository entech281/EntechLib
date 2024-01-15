package org.entechlib.swerve.config;

import org.entechlib.swerve.ConfigConstructionUtil.ControlMethod;
import org.entechlib.swerve.ConfigConstructionUtil.MotorType;

public class MotorConfig {
    private double kP = 0.0;
    private double kI = 0.0;
    private double kD = 0.0;
    private double kFF = 0.0;
    private int currentLimit;
    private double positionConversionFactor;
    private double velocityConversionFactor;
    private ControlMethod controlMethod;
    private MotorType motorType;

    public void setPID(double p, double i, double d) {
        kP = p;
        kI = i;
        kD = d;
    }

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

    public ControlMethod getControlMethod() {
        return this.controlMethod;
    }

    public void setControlMethod(ControlMethod controlMethod) {
        this.controlMethod = controlMethod;
    }

    public MotorType getMotorType() {
        return this.motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }
}
