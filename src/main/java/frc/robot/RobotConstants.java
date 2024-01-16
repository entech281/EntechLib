package frc.robot;

import org.entechlib.swerve.ConfigConstructionUtil.AbsoluteEncoderType;
import org.entechlib.swerve.ConfigConstructionUtil.IMUType;
import org.entechlib.swerve.ConfigConstructionUtil.MotorType;
import org.entechlib.swerve.config.SwerveConfig;
import org.entechlib.swerve.config.SwerveConfigBuilder;

import edu.wpi.first.math.util.Units;

public final class RobotConstants {
    public static final SwerveConfig SWERVE_CONFIG = new SwerveConfigBuilder()
                .getBasicBuilder()
                .withHardware(5676,  6.74603175, 21.4285714, 0.1016)
                .withComponents(MotorType.SPARK_MAX_NEO, MotorType.SPARK_MAX_NEO, AbsoluteEncoderType.THRIFTY)
                .withCurrentLimits(40, 20)
                .withIMU(IMUType.NAVX_MXP, 0, 0, false)
                .withDimensions(Units.inchesToMeters(21.75), Units.inchesToMeters(21.75), 0.39)
                .withRateLimiting(1.2, 1.8, 2.0)
                .withMaxSpeeds(4, 2 * Math.PI)
                .withTurningMotorPID(1.0, 0, 0)
                .withDriveMotorPID(0.04, 0, 0)
                .withAutoTranslationPID(3, 0, 0)
                .withAutoRotationPID(5, 0, 0)
                .withFrontLeftModuleConstants(Ports.CAN.FRONT_LEFT_DRIVING, Ports.CAN.FRONT_LEFT_TURNING, Ports.ANALOG.FRONT_LEFT_TURNING_ABSOLUTE_ENCODER, 2.3084534854898795, false, false)
                .withFrontRightModuleConstants(Ports.CAN.FRONT_RIGHT_DRIVING, Ports.CAN.FRONT_RIGHT_TURNING, Ports.ANALOG.FRONT_RIGHT_TURNING_ABSOLUTE_ENCODER, 1.8754174966340216, false, true)
                .withRearLeftModuleConstants(Ports.CAN.REAR_LEFT_DRIVING, Ports.CAN.REAR_LEFT_TURNING, Ports.ANALOG.REAR_LEFT_TURNING_ABSOLUTE_ENCODER, 2.6789867521760486, false, true)
                .withRearRightModuleConstants(Ports.CAN.REAR_RIGHT_DRIVING, Ports.CAN.REAR_RIGHT_TURNING, Ports.ANALOG.REAR_RIGHT_TURNING_ABSOLUTE_ENCODER, 2.467314041927964, false, true)
                .getConfig();

    public static final int DRIVE_JOYSTICK = 0;
    public static final double DEADBAND = 0.2;
    
    public static interface Ports {
        public static class ANALOG {
            public static final int FRONT_LEFT_TURNING_ABSOLUTE_ENCODER = 1;
            public static final int REAR_LEFT_TURNING_ABSOLUTE_ENCODER = 2;
            public static final int FRONT_RIGHT_TURNING_ABSOLUTE_ENCODER = 0;
            public static final int REAR_RIGHT_TURNING_ABSOLUTE_ENCODER = 3;
        }

        public static class CAN {
            public static final int FRONT_LEFT_DRIVING = 10;
            public static final int REAR_LEFT_DRIVING = 6;
            public static final int FRONT_RIGHT_DRIVING = 21;
            public static final int REAR_RIGHT_DRIVING = 4;

            public static final int FRONT_LEFT_TURNING = 12;
            public static final int REAR_LEFT_TURNING = 5;
            public static final int FRONT_RIGHT_TURNING = 22;
            public static final int REAR_RIGHT_TURNING = 3;
        }

        public static class CONTROLLER {
            public static final double JOYSTICK_AXIS_THRESHOLD = 0.2;
            public static final int JOYSTICK = 0;
            public static final int PANEL = 1;
        }
    }

    private RobotConstants() {
    }
}
