package entechlib.swerve.motors;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import entechlib.swerve.ConfigConstructionUtil.ControlType;
import entechlib.swerve.config.MotorConfig;

public abstract class SparkController implements SwerveMotor {
    protected final CANSparkBase controller;
    protected final SparkPIDController pidController;
    protected final RelativeEncoder encoder;
    protected final ControlType control;

    protected SparkController(CANSparkBase controller, MotorConfig config, boolean inverted) {
        this.controller = controller;
        this.controller.restoreFactoryDefaults();
        pidController = controller.getPIDController();
        encoder = controller.getEncoder();
        pidController.setOutputRange(-1, 1);
        pidController.setFeedbackDevice(encoder);
        this.controller.setIdleMode(IdleMode.kBrake);
        this.controller.setInverted(inverted);
        pidController.setPositionPIDWrappingEnabled(true);
        pidController.setPositionPIDWrappingMinInput(-1);
        pidController.setPositionPIDWrappingMaxInput(1);

        control = config.getControlMethod();

        pidController.setP(config.getP());
        pidController.setI(config.getI());
        pidController.setD(config.getD());
        pidController.setFF(config.getFF());

        this.controller.setSmartCurrentLimit(config.getCurrentLimit());

        encoder.setPositionConversionFactor(config.getPositionConversionFactor());
        encoder.setVelocityConversionFactor(config.getVelocityConversionFactor());

        this.controller.burnFlash();

        this.controller.setInverted(inverted);
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
        if (control == ControlType.POSITION) {
            pidController.setReference(value, com.revrobotics.CANSparkMax.ControlType.kPosition);
        } else {
            pidController.setReference(value, com.revrobotics.CANSparkMax.ControlType.kVelocity);
        }
    }

    @Override
    public boolean getInverted() {
        return controller.getInverted();
    }
}