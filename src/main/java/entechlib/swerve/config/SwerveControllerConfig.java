package entechlib.swerve.config;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class SwerveControllerConfig {
    private double maxSpeedMetersPerSecond;
    private double maxAngularSpeedRadiansPerSecond;

    private PIDConstants translationController;
    private PIDConstants rotationController;

    private SwerveDriveKinematics kinematics;
    private double driveBaseRadius;

    private RateLimiterConfig rateLimiterConfig;

    public double getMaxSpeedMetersPerSecond() {
        return this.maxSpeedMetersPerSecond;
    }

    public void setMaxSpeedMetersPerSecond(double maxSpeedMetersPerSecond) {
        this.maxSpeedMetersPerSecond = maxSpeedMetersPerSecond;
    }

    public double getMaxAngularSpeedRadiansPerSecond() {
        return this.maxAngularSpeedRadiansPerSecond;
    }

    public void setMaxAngularSpeedRadiansPerSecond(double maxAngularSpeedRadiansPerSecond) {
        this.maxAngularSpeedRadiansPerSecond = maxAngularSpeedRadiansPerSecond;
    }

    public PIDConstants getTranslationController() {
        return this.translationController;
    }

    public void setTranslationController(PIDConstants translationController) {
        this.translationController = translationController;
    }

    public PIDConstants getRotationController() {
        return this.rotationController;
    }

    public void setRotationController(PIDConstants rotationController) {
        this.rotationController = rotationController;
    }

    public SwerveDriveKinematics getKinematics() {
        return this.kinematics;
    }

    public void setKinematics(SwerveDriveKinematics kinematics) {
        this.kinematics = kinematics;
    }

    public double getDriveBaseRadius() {
        return this.driveBaseRadius;
    }

    public void setDriveBaseRadius(double driveBaseRadius) {
        this.driveBaseRadius = driveBaseRadius;
    }

    public RateLimiterConfig getRateLimiterConfig() {
        return this.rateLimiterConfig;
    }

    public void setRateLimiterConfig(RateLimiterConfig rateLimiterConfig) {
        this.rateLimiterConfig = rateLimiterConfig;
    }
}