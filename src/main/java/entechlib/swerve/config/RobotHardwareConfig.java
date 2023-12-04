package entechlib.swerve.config;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class RobotHardwareConfig {
    private static final double DEFAULT_MAX_SPEED_METERS_PER_SECOND = 4.0;
    private static final double DEFAULT_MAX_ANGULAR_SPEED_RADIANS_PER_SECOND = 2 * Math.PI;
    public final SwerveDriveKinematics driveKinematics;
    public final double maxSpeedMetersPerSecond;
    public final double maxAngularSpeedRadiansPerSecond;

    public RobotHardwareConfig(double wheelBaseMeters, double trackWidthMeters, double maxSpeedMetersPerSecond,
            double maxAngularSpeedRadiansPerSecond) {
        driveKinematics = new SwerveDriveKinematics(
                new Translation2d(wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(wheelBaseMeters / 2, -trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, -trackWidthMeters / 2));
        this.maxSpeedMetersPerSecond = maxSpeedMetersPerSecond;
        this.maxAngularSpeedRadiansPerSecond = maxAngularSpeedRadiansPerSecond;
    }

    public RobotHardwareConfig(double wheelBaseMeters, double trackWidthMeters) {
        driveKinematics = new SwerveDriveKinematics(
                new Translation2d(wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(wheelBaseMeters / 2, -trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, -trackWidthMeters / 2));
        this.maxSpeedMetersPerSecond = DEFAULT_MAX_SPEED_METERS_PER_SECOND;
        this.maxAngularSpeedRadiansPerSecond = DEFAULT_MAX_ANGULAR_SPEED_RADIANS_PER_SECOND;
    }
}
