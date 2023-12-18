package entechlib.swerve.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import entechlib.swerve.ConfigConstructionUtil.ControlType;

/**
 * A swerve motor for a swerve module, either turning or driving.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SparkMaxNeo implements SwerveMotor {
    private final CANSparkMax controller;
    private final SparkMaxPIDController pidController;
    private final RelativeEncoder encoder;
    private ControlType control;
    private boolean inverted = false;

    public SparkMaxNeo(int id) {
        controller = new CANSparkMax(id, MotorType.kBrushless);
        controller.restoreFactoryDefaults();
        pidController = controller.getPIDController();
        encoder = controller.getEncoder();
        pidController.setOutputRange(-1, 1);
        pidController.setFeedbackDevice(encoder);
        controller.setIdleMode(IdleMode.kBrake);
        controller.setInverted(inverted);
        pidController.setPositionPIDWrappingEnabled(true);
        pidController.setPositionPIDWrappingMinInput(-1);
        pidController.setPositionPIDWrappingMaxInput(1);
    }

    @Override
    public void setControlMethod(ControlType control) {
        this.control = control;
    }

    @Override
    public void setPID(double p, double i, double d, double ff) {
        pidController.setP(p);
        pidController.setI(i);
        pidController.setD(d);
        pidController.setFF(ff);
    }

    @Override
    public void setCurrentLimit(int limit) {
        controller.setSmartCurrentLimit(limit);
    }

    @Override
    public void completeConfigure() {
        controller.burnFlash();
    }

    @Override
    public void setPositionConversionFactor(double positionConversionFactor) {
        encoder.setPositionConversionFactor(positionConversionFactor);
    }

    @Override
    public void setVelocityConversionFactor(double positionConversionFactor) {
        encoder.setVelocityConversionFactor(positionConversionFactor);
    }

    @Override
    public void set(double speed) {
        controller.set(speed);
    }

    @Override
    public void setPosition(double position) {
        encoder.setPosition(position);
    }

    @Override
    public double getPosition() {
        return encoder.getPosition();
    }

    @Override
    public double getVelocity() {
        return encoder.getVelocity();
    }

    @Override
    public void setReference(double value) {
        switch (control) {
            case POSITION:
                pidController.setReference(value, com.revrobotics.CANSparkMax.ControlType.kPosition);
            case VELOCITY:
                pidController.setReference(value, com.revrobotics.CANSparkMax.ControlType.kVelocity);
        }
    }

    @Override
    public boolean getInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
        controller.setInverted(inverted);
    }
}
