package entechlib.swerve;

import entechlib.swerve.config.SwerveConfig;
import entechlib.swerve.encoders.AbsoluteEncoder;
import entechlib.swerve.encoders.ThriftyEncoder;
import entechlib.swerve.exceptions.InvalidTypeException;
import entechlib.swerve.imus.NavxMXP;
import entechlib.swerve.imus.SwerveIMU;
import entechlib.swerve.motors.SparkMaxNeo;
import entechlib.swerve.motors.SwerveMotor;

/**
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public final class ConfigConstructionUtil {
    public enum AbsoluteEncoderType {
        THRIFTY
    }

    public enum MotorType {
        SPARK_MAX_NEO
    }

    public enum ControlType {
        VELOCITY,
        POSITION
    }

    public enum IMUType {
        NAVX_MXP
    }

    public static SwerveMotor getTurningMotor(SwerveConfig swerveConfig, int id, boolean inverted) {
        SwerveMotor motor = null;
        switch (swerveConfig.getTurningMotorType()) {
            case SPARK_MAX_NEO:
                motor = new SparkMaxNeo(id);
                break;
        }
        if (motor == null) {
            throw new InvalidTypeException("Motor", swerveConfig.getTurningMotorType().toString());
        }
        motor.setControlMethod(ControlType.POSITION);
        motor.setVelocityConversionFactor(swerveConfig.getTurningVelocityConversionRadiansPerSecondPerRPM());
        motor.setPositionConversionFactor(swerveConfig.getTurningPositionConversionRadiansPerRotation());
        motor.setPID(swerveConfig.getTurningProportional(), 0, 0, 0);
        motor.setCurrentLimit(swerveConfig.getTurningMotorCurrentLimit());
        motor.setInverted(inverted);
        motor.completeConfigure();
        return motor;
    }

    public static SwerveMotor getDrivingMotor(SwerveConfig swerveConfig, int id, boolean inverted) {
        SwerveMotor motor = null;
        switch (swerveConfig.getDrivingMotorType()) {
            case SPARK_MAX_NEO:
                motor = new SparkMaxNeo(id);
                break;
        }
        if (motor == null) {
            throw new InvalidTypeException("Motor", swerveConfig.getDrivingMotorType().toString());
        }
        motor.setControlMethod(ControlType.VELOCITY);
        motor.setVelocityConversionFactor(swerveConfig.getDrivingVelocityConversionMetersPerSecondPerRPM());
        motor.setPositionConversionFactor(swerveConfig.getDrivingPositionConversionMetersPerRotation());
        motor.setPID(swerveConfig.getDriveProportional(), 0, 0, swerveConfig.getDriveFeedForward());
        motor.setCurrentLimit(swerveConfig.getDriveMotorCurrentLimit());
        motor.setInverted(inverted);
        motor.completeConfigure();
        return motor;
    }

    public static AbsoluteEncoder getAbsoluteEncoder(SwerveConfig swerveConfig, int id, double offsetRadians) {
        AbsoluteEncoder encoder = null;
        switch (swerveConfig.getEncoderType()) {
            case THRIFTY:
                encoder = new ThriftyEncoder(id);
                break;
        }
        if (encoder == null) {
            throw new InvalidTypeException("Encoder", swerveConfig.getEncoderType().toString());
        }
        encoder.setPositionOffset(offsetRadians);
        return encoder;
    }

    public static SwerveIMU getSwerveIMU(SwerveConfig swerveConfig) {
        switch (swerveConfig.getIMUType()) {
            case NAVX_MXP:
                return new NavxMXP();
            default:
                throw new InvalidTypeException("IMU", swerveConfig.getIMUType().toString());
        }
    }

    private ConfigConstructionUtil() {
    }
}
