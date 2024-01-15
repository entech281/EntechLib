package entechlib.swerve.config;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import entechlib.swerve.ConfigConstructionUtil.AbsoluteEncoderType;
import entechlib.swerve.ConfigConstructionUtil.IMUType;
import entechlib.swerve.ConfigConstructionUtil.MotorType;

/**
 * The {@link SwerveConfigBuilder} lets you configure a working swerve config file to ensure all parameters are set.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SwerveConfigBuilder {
    public MechanicalBuilder getBasicBuilder() {
        return new Builder();
    }

    /** Configure the basic module hardware  */
    public interface MechanicalBuilder {
        /**
         * Set the basic mechanical properties of the swerve modules.
         * 
         * 
         * @param driveMotorFreeSpeed The free speed of the drive motor.
         * @param driveMotorReduction The reduction of the drive motor to you wheel.
         * @param turningMotorReduction The reduction of the turning motor to the rotation of the wheel.
         * @param wheelDiameterMeters The diameter of the wheel in meters.
         */
        ComponentsBuilder withHardware(double driveMotorFreeSpeed, double driveMotorReduction, double turningMotorReduction, double wheelDiameterMeters);
    }

    /** Configure the component types used. */
    public interface ComponentsBuilder {
        /**
         * Set the types of components used on the swerve modules.
         * 
         * 
         * @param drivingType The type of driving motor.
         * @param turningType The type of turning motor.
         * @param encoderType The type of absolute encoder.
         */
        CurrentLimitBuilder withComponents(MotorType drivingType, MotorType turningType, AbsoluteEncoderType encoderType);
    }

    /** Configure the current limits. */
    public interface CurrentLimitBuilder {
        /**
         * Set the current limits of the motors.
         * 
         * 
         * @param driveMotorCurrentLimit The drive motor current limit.
         * @param turningMotorCurrentLimit The turning motor current limit.
         */
        IMUBuilder withCurrentLimits(int driveMotorCurrentLimit, int turningMotorCurrentLimit);
    }

    /** Configure the IMU. */
    public interface IMUBuilder {
        /**
         * Set the type and properties of the IMU on the robot.
         * 
         * 
         * @param type The type of IMU.
         * @param id The ID of the IMU. (analog port, usb port, can, or 0 if it's a MXP port IMU)
         * @param offset The offset of the IMU from forward heading.
         * @param inverted If the IMU is inverted.
         */
        KinematicsBuilder withIMU(IMUType type, int id, double offset, boolean inverted);
    }

    /** Configure the size of the robot. */
    public interface KinematicsBuilder {
        /**
         * Set the size of the robot for module state calculations and odometry.
         * 
         * 
         * @param wheelBaseMeters If the wheels are facing forward, the distance between the center of both left or bot right wheels in meters.
         * @param trackWidthMeters If the wheels are facing forward, the distance between the center of the grip of the both rear or front wheels in meters.
         * @param driveBaseRadiusMeters The distance from the center of the robot to the center of the farthest module in meters.
         */
        RateLimitingBuilder withDimensions(double wheelBaseMeters, double trackWidthMeters, double driveBaseRadiusMeters);
    }

    /** Configure rate limiting. */
    public interface RateLimitingBuilder {
        /**
         * Limit the rate of acceleration with the given rates.
         * 
         * 
         * @param directionalSlewRate
         * @param magnitudeSlewRate
         * @param rotationalSlewRate
         */
        MaxSpeedBuilder withRateLimiting(double directionalSlewRate, double magnitudeSlewRate, double rotationalSlewRate);
        /**
         * No rate limiting.
         */
        MaxSpeedBuilder withoutRateLimiting();
    }

    /** Configure the max speed of the robot. */
    public interface MaxSpeedBuilder {
        /**
         * Configure the max speeds of the robot.
         * 
         * 
         * @param maxMetersPerSecond The max speed of the robot in meters per second.
         * @param maxAngularRadiansPerSecond The max angular speed of the robot in radians per second.
         */
        TurningMotorPIDBuilder withMaxSpeeds(double maxMetersPerSecond, double maxAngularRadiansPerSecond);
    }

    /** Configure the turning motors PID controller. */
    public interface TurningMotorPIDBuilder {
        /**
         * Configure the pid values for the turning motors PID controller.
         * 
         * 
         * @param p
         * @param i
         * @param d
         */
        DriveMotorPIDBuilder withTurningMotorPID(double p, double i, double d);
    }

    /** Configure the drive motors PID controller. */
    public interface DriveMotorPIDBuilder {
        /**
         * Configure the pid values for the drive motors PID controller.
         * 
         * 
         * @param p
         * @param i
         * @param d
         */
        AutoTranslationPIDBuilder withDriveMotorPID(double p, double i, double d);
    }

    /** Configure the autonomous translation PID controller. */
    public interface AutoTranslationPIDBuilder {
        /**
         * Configure the pid values for the autonomous translation PID controller.
         * 
         * 
         * @param p
         * @param i
         * @param d
         */
        AutoRotationPIDBuilder withAutoTranslationPID(double p, double i, double d);
    }

    /** Configure the autonomous rotation PID controller. */
    public interface AutoRotationPIDBuilder {
        /**
         * Configure the pid values for the autonomous rotation PID controller.
         * 
         * 
         * @param p
         * @param i
         * @param d
         */
        FrontLeftBuilder withAutoRotationPID(double p, double i, double d);
    }

    /** Configure the front left swerve module. */
    public interface FrontLeftBuilder {
        /**
         * Configure the front left swerve module IDs and basic settings.
         * 
         * 
         * @param driveMotorID Front left drive motor ID.
         * @param turningMotorID Front left turning motor ID.
         * @param absoluteEncoderID Front left absolute encoder ID.
         * @param absoluteEncoderOffsetRadians Front left absolute encoder offset.
         * @param driveMotorInverted Front left drive motor inverted.
         * @param turningMotorInverted Front left turning motor inverted.
         */
        FrontRightBuilder withFrontLeftModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    /** Configure the front right swerve module. */
    public interface FrontRightBuilder {
        /**
         * Configure the front right swerve module IDs and basic settings.
         * 
         * 
         * @param driveMotorID Front right drive motor ID.
         * @param turningMotorID Front right turning motor ID.
         * @param absoluteEncoderID Front right absolute encoder ID.
         * @param absoluteEncoderOffsetRadians Front right absolute encoder offset.
         * @param driveMotorInverted Front right drive motor inverted.
         * @param turningMotorInverted Front right turning motor inverted.
         */
        RearLeftBuilder withFrontRightModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    /** Configure the rear left swerve module. */
    public interface RearLeftBuilder {
        /**
         * Configure the rear left swerve module IDs and basic settings.
         * 
         * 
         * @param driveMotorID Rear left drive motor ID.
         * @param turningMotorID Rear left turning motor ID.
         * @param absoluteEncoderID Rear left absolute encoder ID.
         * @param absoluteEncoderOffsetRadians Rear left absolute encoder offset.
         * @param driveMotorInverted Rear left drive motor inverted.
         * @param turningMotorInverted Rear left turning motor inverted.
         */
        RearRightBuilder withRearLeftModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    /** Configure the rear right swerve module. */
    public interface RearRightBuilder {
        /**
         * Configure the rear right swerve module IDs and basic settings.
         * 
         * 
         * @param driveMotorID Rear right drive motor ID.
         * @param turningMotorID Rear right turning motor ID.
         * @param absoluteEncoderID Rear right absolute encoder ID.
         * @param absoluteEncoderOffsetRadians Rear right absolute encoder offset.
         * @param driveMotorInverted Rear right drive motor inverted.
         * @param turningMotorInverted Rear right turning motor inverted.
         */
        ConfigCompleteBuilder withRearRightModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    /** The configuration is complete and you can now get the config object. */
    public interface ConfigCompleteBuilder {
        /** @return The completed config object. */
        SwerveConfig getConfig();
    }

    public class Builder implements MechanicalBuilder, ComponentsBuilder, IMUBuilder, KinematicsBuilder, RateLimitingBuilder, MaxSpeedBuilder, CurrentLimitBuilder, TurningMotorPIDBuilder, DriveMotorPIDBuilder, AutoTranslationPIDBuilder, AutoRotationPIDBuilder, FrontLeftBuilder, FrontRightBuilder, RearLeftBuilder, RearRightBuilder, ConfigCompleteBuilder {
        private final SwerveConfig config = new SwerveConfig();

        @Override
        public ComponentsBuilder withHardware(double driveMotorFreeSpeed, double driveMotorReduction, double turningMotorReduction,
                double wheelDiameterMeters) {
            double drivingPositionConversionMetersPerRotation = (wheelDiameterMeters * Math.PI) / driveMotorReduction;
            config.hardwareConfig.drivingMotorConfig.setPositionConversionFactor(drivingPositionConversionMetersPerRotation);

            double drivingVelocityConversionMetersPerSecondPerRPM = ((wheelDiameterMeters * Math.PI)
                    / driveMotorReduction) / 60;
            config.hardwareConfig.drivingMotorConfig.setVelocityConversionFactor(drivingVelocityConversionMetersPerSecondPerRPM);
    
            double turningPositionConversionRadiansPerRotation = (2 * Math.PI) / turningMotorReduction;
            config.hardwareConfig.turningMotorConfig.setPositionConversionFactor(turningPositionConversionRadiansPerRotation);
    
            double turningVelocityConversionRadiansPerSecondPerRPM = (2 * Math.PI) / turningMotorReduction / 60;
            config.hardwareConfig.turningMotorConfig.setVelocityConversionFactor(turningVelocityConversionRadiansPerSecondPerRPM);
    
            double driveFF = 1 / (((wheelDiameterMeters * Math.PI) * driveMotorFreeSpeed) / driveMotorReduction);
            config.hardwareConfig.drivingMotorConfig.setFF(driveFF);
            
            return this;
        }

        @Override
        public CurrentLimitBuilder withComponents(MotorType drivingType, MotorType turningType, AbsoluteEncoderType encoderType) {
            config.hardwareConfig.drivingMotorConfig.setMotorType(drivingType);
            config.hardwareConfig.turningMotorConfig.setMotorType(turningType);
            config.hardwareConfig.setEncoderType(encoderType);
            return this;
        }

        @Override
        public IMUBuilder withCurrentLimits(int driveMotorCurrentLimit, int turningMotorCurrentLimit) {
            config.hardwareConfig.drivingMotorConfig.setCurrentLimit(driveMotorCurrentLimit);
            config.hardwareConfig.turningMotorConfig.setCurrentLimit(turningMotorCurrentLimit);
            return this;
        }

        @Override
        public KinematicsBuilder withIMU(IMUType type, int id, double offset, boolean inverted) {
            config.hardwareConfig.setGyroType(type);
            config.hardwareConfig.setGyroOffset(offset);
            config.hardwareConfig.setGyroInverted(inverted);
            config.hardwareConfig.setGyroID(id);
            return this;
        }

        @Override
        public RateLimitingBuilder withDimensions(double wheelBaseMeters, double trackWidthMeters, double driveBaseRadiusMeters) {
            config.controllerConfig.setDriveBaseRadius(driveBaseRadiusMeters);
            config.controllerConfig.setKinematics(new SwerveDriveKinematics(
                new Translation2d(wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(wheelBaseMeters / 2, -trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, -trackWidthMeters / 2)));
            return this;
        }

        @Override
        public MaxSpeedBuilder withRateLimiting(double directionalSlewRate, double magnitudeSlewRate,
                double rotationalSlewRate) {
            config.controllerConfig.setRateLimiterConfig(new RateLimiterConfig(directionalSlewRate, magnitudeSlewRate, rotationalSlewRate));
            return this;
        }

        @Override
        public MaxSpeedBuilder withoutRateLimiting() {
            config.controllerConfig.setRateLimiterConfig(null);
            return this;
        }

        @Override
        public TurningMotorPIDBuilder withMaxSpeeds(double maxMetersPerSecond, double maxAngularRadiansPerSecond) {
            config.controllerConfig.setMaxSpeedMetersPerSecond(maxMetersPerSecond);
            config.controllerConfig.setMaxAngularSpeedRadiansPerSecond(maxAngularRadiansPerSecond);
            return this;
        }

        @Override
        public DriveMotorPIDBuilder withTurningMotorPID(double p, double i, double d) {
            config.hardwareConfig.turningMotorConfig.setPID(p, i, d);
            return this;
        }

        @Override
        public AutoTranslationPIDBuilder withDriveMotorPID(double p, double i, double d) {
            config.hardwareConfig.drivingMotorConfig.setPID(p, i, d);
            return this;
        }

        @Override
        public AutoRotationPIDBuilder withAutoTranslationPID(double p, double i, double d) {
            config.controllerConfig.setTranslationController(new PIDConstants(p, i, d));
            return this;
        }

        @Override
        public FrontLeftBuilder withAutoRotationPID(double p, double i, double d) {
            config.controllerConfig.setRotationController(new PIDConstants(p, i, d));
            return this;
        }

        private void moduleConfigHelper(ModuleConfig module, int driveMotorID, int turningMotorID,
                int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted,
                boolean turningMotorInverted) {
            module.setDriveMotorID(driveMotorID);
            module.setTurningMotorID(turningMotorID);
            module.setAbsoluteEncoderID(absoluteEncoderID);
            module.setEncoderOffsetRadians(absoluteEncoderOffsetRadians);
            module.setDrivingMotorInverted(driveMotorInverted);
            module.setTurningMotorInverted(turningMotorInverted);
        }

        @Override
        public FrontRightBuilder withFrontLeftModuleConstants(int driveMotorID, int turningMotorID,
                int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted,
                boolean turningMotorInverted) {
            moduleConfigHelper(config.hardwareConfig.frontLeftConfig, driveMotorID, turningMotorID, absoluteEncoderID, absoluteEncoderOffsetRadians, driveMotorInverted, turningMotorInverted);
            return this;
        }

        @Override
        public RearLeftBuilder withFrontRightModuleConstants(int driveMotorID, int turningMotorID,
                int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted,
                boolean turningMotorInverted) {
            moduleConfigHelper(config.hardwareConfig.frontRightConfig, driveMotorID, turningMotorID, absoluteEncoderID, absoluteEncoderOffsetRadians, driveMotorInverted, turningMotorInverted);
            return this;
        }

        @Override
        public RearRightBuilder withRearLeftModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID,
                double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted) {
            moduleConfigHelper(config.hardwareConfig.rearLeftConfig, driveMotorID, turningMotorID, absoluteEncoderID, absoluteEncoderOffsetRadians, driveMotorInverted, turningMotorInverted);
            return this;
        }

        @Override
        public ConfigCompleteBuilder withRearRightModuleConstants(int driveMotorID, int turningMotorID,
                int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted,
                boolean turningMotorInverted) {
            moduleConfigHelper(config.hardwareConfig.rearRightConfig, driveMotorID, turningMotorID, absoluteEncoderID, absoluteEncoderOffsetRadians, driveMotorInverted, turningMotorInverted);
            return this;
        }

        @Override
        public SwerveConfig getConfig() {
            return config;
        }
    }
}
