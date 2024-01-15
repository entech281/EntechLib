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
                .withFrontLeftModuleConstants(10, 12, 1, 2.3084534854898795, false, true)
                .withFrontRightModuleConstants(21, 22, 0, 1.8754174966340216, false, true)
                .withRearLeftModuleConstants(6, 5, 2, 2.6789867521760486, false, true)
                .withRearRightModuleConstants(4, 3, 3, 2.467314041927964, false, true)
                .getConfig();

    public static final int DRIVE_JOYSTICK = 0;
    public static final double DEADBAND = 0.2;
    private RobotConstants() {
    }
}
