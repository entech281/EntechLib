package entechlib.swerve.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import entechlib.swerve.config.MotorConfig;

public class SparkMaxNeo {
    private final CANSparkMax controller;

    public SparkMaxNeo(int id) {
        controller = new CANSparkMax(id, MotorType.kBrushless);
    }

    public void configure(MotorConfig config) {
        controller.restoreFactoryDefaults();
        controller.setSmartCurrentLimit(config.currentLimit);
        controller.setIdleMode(IdleMode.kBrake);

        RelativeEncoder encoder = controller.getEncoder();
        SparkMaxPIDController pidController = controller.getPIDController();

        pidController.setFeedbackDevice(encoder);
        pidController.setP(config.p);
        pidController.setI(config.i);
        pidController.setD(config.d);
        pidController.setFF(config.ff);
        pidController.setOutputRange(-1, 1);

        encoder.setPositionConversionFactor(config.positionFactor);
        encoder.setVelocityConversionFactor(config.velocityFactor);

        controller.burnFlash();
    }

    public void set(double speed) {
        controller.set(speed);
    }

    public void setPosition(double position) {
        controller.getEncoder().setPosition(position);
    }

    public double getPosition() {
        return controller.getEncoder().getPosition();
    }

    public double getVelocity() {
        return controller.getEncoder().getVelocity();
    }

    public void setReference(double value, ControlType control) {
        controller.getPIDController().setReference(value, control);
    }
}
