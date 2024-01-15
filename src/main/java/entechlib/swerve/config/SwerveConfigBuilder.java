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

    public interface MechanicalBuilder {
        ComponentsBuilder withHardware(double driveMotorFreeSpeed, double driveMotorReduction, double turningMotorReduction, double wheelDiameterMeters);
    }

    public interface ComponentsBuilder {
        CurrentLimitBuilder withComponents(MotorType drivingType, MotorType turningType, AbsoluteEncoderType encoderType);
    }

    public interface CurrentLimitBuilder {
        IMUBuilder withCurrentLimits(int driveMotorCurrentLimit, int turningMotorCurrentLimit);
    }

    public interface IMUBuilder {
        KinematicsBuilder withIMU(IMUType type, int id, double offset, boolean inverted);
    }

    public interface KinematicsBuilder {
        RateLimitingBuilder withDimensions(double wheelBaseMeters, double trackWidthMeters, double driveBaseRadiusMeters);
    }

    public interface RateLimitingBuilder {
        MaxSpeedBuilder withRateLimiting(double directionalSlewRate, double magnitudeSlewRate, double rotationalSlewRate);
        MaxSpeedBuilder withoutRateLimiting();
    }

    public interface MaxSpeedBuilder {
        TurningMotorPIDBuilder withMaxSpeeds(double maxMetersPerSecond, double maxAngularRadiansPerSecond);
    }

    public interface TurningMotorPIDBuilder {
        DriveMotorPIDBuilder withTurningMotorPID(double p, double i, double d);
    }

    public interface DriveMotorPIDBuilder {
        AutoTranslationPIDBuilder withDriveMotorPID(double p, double i, double d);
    }

    public interface AutoTranslationPIDBuilder {
        AutoRotationPIDBuilder withAutoTranslationPID(double p, double i, double d);
    }

    public interface AutoRotationPIDBuilder {
        FrontLeftBuilder withAutoRotationPID(double p, double i, double d);
    }

    public interface FrontLeftBuilder {
        FrontRightBuilder withFrontLeftModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    public interface FrontRightBuilder {
        RearLeftBuilder withFrontRightModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    public interface RearLeftBuilder {
        RearRightBuilder withRearLeftModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    public interface RearRightBuilder {
        ConfigCompleteBuilder withRearRightModuleConstants(int driveMotorID, int turningMotorID, int absoluteEncoderID, double absoluteEncoderOffsetRadians, boolean driveMotorInverted, boolean turningMotorInverted);
    }

    public interface ConfigCompleteBuilder {
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
