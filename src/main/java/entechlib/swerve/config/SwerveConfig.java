package entechlib.swerve.config;

public class SwerveConfig {
    private boolean rateLimiting = false;
    private RateLimiterConfig rateLimiterConfig = null;

    public final ElectronicsConfig electronicsConfig;
    public final RobotHardwareConfig hardwareConfig;

    public SwerveConfig(ElectronicsConfig electronicsConfig, RobotHardwareConfig hardwareConfig) {
        this.electronicsConfig = electronicsConfig;
        this.hardwareConfig = hardwareConfig;
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
