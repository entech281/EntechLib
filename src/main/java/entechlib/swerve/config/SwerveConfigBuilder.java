package entechlib.swerve.config;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import entechlib.swerve.ConfigConstructionUtil.AbsoluteEncoderType;
import entechlib.swerve.ConfigConstructionUtil.IMUType;
import entechlib.swerve.ConfigConstructionUtil.MotorType;

public class SwerveConfigBuilder {
    public MechanicalBuilder getBasicBuilder() {
        return new Builder();
    }

    public interface MechanicalBuilder {
        ElectricalBuilder withHardware(double motorFreeSpeed, double driveMotorReduction, double turningMotorReduction, double wheelDiameterMeters);
    }

    public interface ElectricalBuilder {
        IMUBuilder withComponents(MotorType drivingType, MotorType turningType, AbsoluteEncoderType encoderType);
    }

    public interface IMUBuilder {
        KinematicsBuilder withIMU(IMUType type, int id, double offset, boolean inverted);
    }

    public interface KinematicsBuilder {
        RateLimitingBuilder withDimensions(double wheelBaseMeters, double trackWidthMeters);
    }

    public interface RateLimitingBuilder {
        MaxSpeedBuilder withRateLimiting(double directionalSlewRate, double magnitudeSlewRate, double rotationalSlewRate);
        MaxSpeedBuilder withoutRateLimiting();
    }

    public interface MaxSpeedBuilder {
        void withMaxSpeeds(double maxMetersPerSecond, double maxAngularRadiansPerSecond);
    }

    public class Builder implements MechanicalBuilder, ElectricalBuilder, IMUBuilder, KinematicsBuilder, RateLimitingBuilder, MaxSpeedBuilder {
        private final TempSwerveConfig config = new TempSwerveConfig();

        @Override
        public ElectricalBuilder withHardware(double motorFreeSpeed, double driveMotorReduction, double turningMotorReduction,
                double wheelDiameterMeters) {
            double drivingPositionConversionMetersPerRotation = (wheelDiameterMeters * Math.PI) / driveMotorReduction;
            config.getHardwareConfig().getDrivingMotorConfig().setPositionConversionFactor(drivingPositionConversionMetersPerRotation);

            double drivingVelocityConversionMetersPerSecondPerRPM = ((wheelDiameterMeters * Math.PI)
                    / driveMotorReduction) / 60;
            config.getHardwareConfig().getDrivingMotorConfig().setVelocityConversionFactor(drivingVelocityConversionMetersPerSecondPerRPM);
    
            double turningPositionConversionRadiansPerRotation = (2 * Math.PI) / turningMotorReduction;
            config.getHardwareConfig().getTurningMotorConfig().setPositionConversionFactor(turningPositionConversionRadiansPerRotation);
    
            double turningVelocityConversionRadiansPerSecondPerRPM = (2 * Math.PI) / turningMotorReduction / 60;
            config.getHardwareConfig().getTurningMotorConfig().setVelocityConversionFactor(turningVelocityConversionRadiansPerSecondPerRPM);
    
            double driveFF = 1 / (((wheelDiameterMeters * Math.PI) * motorFreeSpeed) / driveMotorReduction);
            config.getHardwareConfig().getDrivingMotorConfig().setFF(driveFF);
            
            return this;
        }

        @Override
        public IMUBuilder withComponents(MotorType drivingType, MotorType turningType, AbsoluteEncoderType encoderType) {
            config.getHardwareConfig().getDrivingMotorConfig().setMotorType(drivingType);
            config.getHardwareConfig().getTurningMotorConfig().setMotorType(turningType);
            config.getHardwareConfig().setEncoderType(encoderType);
            return this;
        }

        @Override
        public KinematicsBuilder withIMU(IMUType type, int id, double offset, boolean inverted) {
            config.getHardwareConfig().setGyroType(type);
            config.getHardwareConfig().setGyroOffset(offset);
            config.getHardwareConfig().setGyroInverted(inverted);
            config.getHardwareConfig().setGyroID(id);
            return this;
        }

        @Override
        public RateLimitingBuilder withDimensions(double wheelBaseMeters, double trackWidthMeters) {
            config.getControllerConfig().setKinematics(new SwerveDriveKinematics(
                new Translation2d(wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(wheelBaseMeters / 2, -trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, -trackWidthMeters / 2)));
            return this;
        }

        @Override
        public MaxSpeedBuilder withRateLimiting(double directionalSlewRate, double magnitudeSlewRate,
                double rotationalSlewRate) {
            config.getControllerConfig().setRateLimiterConfig(new RateLimiterConfig(directionalSlewRate, magnitudeSlewRate, rotationalSlewRate));
            return this;
        }

        @Override
        public MaxSpeedBuilder withoutRateLimiting() {
            config.getControllerConfig().setRateLimiterConfig(null);
            return this;
        }

        @Override
        public void withMaxSpeeds(double maxMetersPerSecond, double maxAngularRadiansPerSecond) {
            config.getControllerConfig().setMaxSpeedMetersPerSecond(maxMetersPerSecond);
            config.getControllerConfig().setMaxAngularSpeedRadiansPerSecond(maxAngularRadiansPerSecond);
        }
    }
}
