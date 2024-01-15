package entechlib.swerve;

import entechlib.swerve.config.MotorConfig;
import entechlib.swerve.config.SwerveHardwareConfig;
import entechlib.swerve.encoders.AbsoluteEncoder;
import entechlib.swerve.encoders.CanCoder;
import entechlib.swerve.encoders.ThriftyEncoder;
import entechlib.swerve.exceptions.InvalidTypeException;
import entechlib.swerve.imus.NavxMXP;
import entechlib.swerve.imus.NavxUSB;
import entechlib.swerve.imus.SwerveIMU;
import entechlib.swerve.motors.SparkFlexVortex;
import entechlib.swerve.motors.SparkMaxNeo;
import entechlib.swerve.motors.SwerveMotor;

/**
 * Contains methods that make and configure hardware.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public final class ConfigConstructionUtil {
    /**
     * Different supported hardware options for absolute encoders.
     */
    public enum AbsoluteEncoderType {
        THRIFTY,
        CANCODER
    }

    /**
     * Different supported hardware options for turning or driving motors on a swerve module.
     */
    public enum MotorType {
        SPARK_MAX_NEO,
        SPARK_FLEX_VORTEX
    }

    /**
     * Methods of control for a swerve motor.
     */
    public enum ControlMethod {
        VELOCITY,
        POSITION
    }

    /**
     * Different supported hardware options for robot IMUs.
     */
    public enum IMUType {
        NAVX_MXP,
        NAVX_USB
    }

    /**
     * Makes and configures a swerve module's motor.
     * 
     * 
     * @param config
     * @param id
     * @param inverted
     * @return Configured swerve motor of provided settings.
     */
    public static SwerveMotor getMotor(MotorConfig config, int id, boolean inverted) {
        SwerveMotor motor = null;
        switch (config.getMotorType()) {
            case SPARK_MAX_NEO:
                motor = new SparkMaxNeo(id, config, inverted);
                break;
            case SPARK_FLEX_VORTEX:
                motor = new SparkFlexVortex(id, config, inverted);
                break;
            default:
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
     * @return Configured absolute encoder of provided settings.
     */
    public static AbsoluteEncoder getAbsoluteEncoder(AbsoluteEncoderType type, int id, double offsetRadians) {
        AbsoluteEncoder encoder = null;
        switch (type) {
            case THRIFTY:
                encoder = new ThriftyEncoder(id);
                break;
            case CANCODER:
                encoder = new CanCoder(id);
                break;
            default:
                throw new InvalidTypeException("Encoder", type.toString());
        }
        encoder.setPositionOffset(offsetRadians);
        return encoder;
    }


    /**
     * Makes a swerve bot's IMU.
     * 
     * 
     * @param swerveConfig
     * @return IMU of provided settings.
     */
    public static SwerveIMU getSwerveIMU(SwerveHardwareConfig swerveConfig) {
        switch (swerveConfig.getGyroType()) {
            case NAVX_MXP:
                return new NavxMXP();
            case NAVX_USB:
                return new NavxUSB(swerveConfig.getGyroID());
            default:
                throw new InvalidTypeException("IMU", swerveConfig.getGyroType().toString());
        }
    }

    private ConfigConstructionUtil() {
    }
}
