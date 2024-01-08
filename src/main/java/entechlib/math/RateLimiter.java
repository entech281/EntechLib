package entechlib.math;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.util.WPIUtilJNI;
import entechlib.input.DriveInput;
import entechlib.swerve.SwerveUtils;
import entechlib.swerve.config.RateLimiterConfig;

public class RateLimiter {
    private final double directionalSlewRate;

    public RateLimiter(RateLimiterConfig config) {
        magLimiter = new SlewRateLimiter(config.getMagnitudeSlewRate());
        rotLimiter = new SlewRateLimiter(config.getRotationalSlewRate());
        directionalSlewRate = config.getDirectionalSlewRate();
    }

    private double currentTranslationDir = 0.0;
    private double currentTranslationMag = 0.0;

    private final SlewRateLimiter magLimiter;
    private final SlewRateLimiter rotLimiter;
    private double m_prevTime = WPIUtilJNI.now() * 1e-6;

    public DriveInput limit(DriveInput input) {
        // Convert XY to polar for rate limiting
        double inputTranslationDir = Math.atan2(input.getYSpeed(), input.getXSpeed());
        double inputTranslationMag = Math
                .sqrt(Math.pow(input.getXSpeed(), 2) + Math.pow(input.getYSpeed(), 2));

        // Calculate the direction slew rate based on an estimate of the lateral
        // acceleration
        double directionSlewRate;

        if (currentTranslationMag != 0.0) {
            directionSlewRate = Math.abs(directionalSlewRate / currentTranslationMag);
        } else {
            directionSlewRate = 500.0; // some high number that means the slew rate is effectively instantaneous
        }

        double currentTime = WPIUtilJNI.now() * 1e-6;
        double elapsedTime = currentTime - m_prevTime;
        double angleDif = SwerveUtils.AngleDifference(inputTranslationDir, currentTranslationDir);

        if (angleDif < 0.45 * Math.PI) {
            currentTranslationDir = SwerveUtils.StepTowardsCircular(currentTranslationDir,
                    inputTranslationDir,
                    directionSlewRate * elapsedTime);
            currentTranslationMag = magLimiter.calculate(inputTranslationMag);
        } else if (angleDif > 0.85 * Math.PI) {
            if (currentTranslationMag > 1e-4) {
                currentTranslationMag = magLimiter.calculate(0.0);
            } else {
                currentTranslationDir = SwerveUtils.WrapAngle(currentTranslationDir + Math.PI);
                currentTranslationMag = magLimiter.calculate(inputTranslationMag);
            }
        } else {
            currentTranslationDir = SwerveUtils.StepTowardsCircular(currentTranslationDir,
                    inputTranslationDir,
                    directionSlewRate * elapsedTime);
            currentTranslationMag = magLimiter.calculate(0.0);
        }

        m_prevTime = currentTime;

        double limitedX = currentTranslationMag * Math.cos(currentTranslationDir);
        double limitedY = currentTranslationMag * Math.sin(currentTranslationDir);
        double limitedRotation = rotLimiter.calculate(input.getRotationSpeed());
        return new DriveInput(limitedX, limitedY, limitedRotation);
    }
}
