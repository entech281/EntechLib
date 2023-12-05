package entechlib.swerve.config;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class SwerveConfig {
    private boolean rateLimiting = false;
    private RateLimiterConfig rateLimiterConfig = null;

    public final ElectronicsConfig electronicsConfig;
    public SwerveDriveKinematics driveKinematics;
    /** Default value is 4 */
    public double maxSpeedMetersPerSecond = 4.0;
    /** Default value is 2PI */
    public double maxAngularSpeedRadiansPerSecond = 2 * Math.PI;

    public void setRobotHardware(double wheelBaseMeters, double trackWidthMeters) {
        driveKinematics = new SwerveDriveKinematics(
                new Translation2d(wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(wheelBaseMeters / 2, -trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, trackWidthMeters / 2),
                new Translation2d(-wheelBaseMeters / 2, -trackWidthMeters / 2));
    }

    public SwerveConfig(ElectronicsConfig electronicsConfig) {
        this.electronicsConfig = electronicsConfig;
    }

    public RateLimiterConfig getRateLimiterConfig() {
        return rateLimiterConfig;
    }

    public boolean isRateLimiting() {
        return rateLimiting;
    }

    public void withRateLimiting(RateLimiterConfig rateLimiterConfig) {
        this.rateLimiterConfig = rateLimiterConfig;
        rateLimiting = true;
    }
}
