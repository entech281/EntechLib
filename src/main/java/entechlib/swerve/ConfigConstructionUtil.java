package entechlib.swerve;

import entechlib.swerve.config.MotorConfig;
import entechlib.swerve.config.SwerveHardwareConfig;
import entechlib.swerve.encoders.AbsoluteEncoder;
import entechlib.swerve.encoders.ThriftyEncoder;
import entechlib.swerve.exceptions.InvalidTypeException;
import entechlib.swerve.imus.NavxMXP;
import entechlib.swerve.imus.SwerveIMU;
import entechlib.swerve.motors.SparkMaxNeo;
import entechlib.swerve.motors.SwerveMotor;

/**
 * Contains methods that make and configure hardware.
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

    /**
     * Makes and configures a swerve module's motor.
     * 
     * 
     * @param swerveConfig
     * @param id
     * @param inverted
     * @return
     */
    public static SwerveMotor getMotor(MotorConfig config, int id, boolean inverted) {
        SwerveMotor motor = null;
        switch (config.getMotorType()) {
            case SPARK_MAX_NEO:
                motor = new SparkMaxNeo(id, config, inverted);
                break;
        }
        if (motor == null) {
            throw new InvalidTypeException("Motor", config.getMotorType().toString());
        }
        return motor;
    }

    /**
     * Makes and configures a swerve module's absolute encoder.
     * 
     * 
     * @param swerveConfig
     * @param id
     * @param offsetRadians
     * @return
     */
    public static AbsoluteEncoder getAbsoluteEncoder(AbsoluteEncoderType type, int id, double offsetRadians) {
        AbsoluteEncoder encoder = null;
        switch (type) {
            case THRIFTY:
                encoder = new ThriftyEncoder(id);
                break;
        }
        if (encoder == null) {
            throw new InvalidTypeException("Encoder", type.toString());
        }
        encoder.setPositionOffset(offsetRadians);
        return encoder;
    }


    /**
     * Makes and configures a swerve bot's IMU.
     * 
     * 
     * @param swerveConfig
     * @return
     */
    public static SwerveIMU getSwerveIMU(SwerveHardwareConfig swerveConfig) {
        switch (swerveConfig.getGyroType()) {
            case NAVX_MXP:
                return new NavxMXP();
            default:
                throw new InvalidTypeException("IMU", swerveConfig.getGyroType().toString());
        }
    }

    private ConfigConstructionUtil() {
    }
}
