package entechlib.math;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.util.WPIUtilJNI;
import entechlib.input.DriveInput;
import entechlib.swerve.SwerveUtils;
import entechlib.swerve.config.RateLimiterConfig;

/**
 * The {@link RateLimiter} class limits the rate of acceleration allowing for more fluid driving of the robot.
 */
public class RateLimiter {
    private final double directionalSlewRate;

    /**
     * Creates a {@link RateLimiter} with the give {@link RateLimiterConfig}.
     * 
     * 
     * @param config
     */
    public RateLimiter(RateLimiterConfig config) {
        magLimiter = new SlewRateLimiter(config.getMagnitudeSlewRate());
        rotLimiter = new SlewRateLimiter(config.getRotationalSlewRate());
        directionalSlewRate = config.getDirectionalSlewRate();
    }

    private double currentTranslationDir = 0.0;
    private double currentTranslationMag = 0.0;

    private final SlewRateLimiter magLimiter;
    private final SlewRateLimiter rotLimiter;
    private double prevTime = WPIUtilJNI.now() * 1e-6;

    /**
     * Limits the rate of the acceleration of the {@link DriveInput} to the swerve controller.
     * 
     * TODO: Reverse engineer this method and figure out how it works, then break it double in to well documented smalled methods.
     * 
     * @param input
     * @return a {@link DriveInput} object with the limited values.
     */
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
        double elapsedTime = currentTime - prevTime;
        double angleDif = SwerveUtils.angleDifference(inputTranslationDir, currentTranslationDir);

        if (angleDif < 0.45 * Math.PI) {
            currentTranslationDir = SwerveUtils.stepTowardsCircular(currentTranslationDir,
                    inputTranslationDir,
                    directionSlewRate * elapsedTime);
            currentTranslationMag = magLimiter.calculate(inputTranslationMag);
        } else if (angleDif > 0.85 * Math.PI) {
            if (currentTranslationMag > 1e-4) {
                currentTranslationMag = magLimiter.calculate(0.0);
            } else {
                currentTranslationDir = SwerveUtils.wrapAngle(currentTranslationDir + Math.PI);
                currentTranslationMag = magLimiter.calculate(inputTranslationMag);
            }
        } else {
            currentTranslationDir = SwerveUtils.stepTowardsCircular(currentTranslationDir,
                    inputTranslationDir,
                    directionSlewRate * elapsedTime);
            currentTranslationMag = magLimiter.calculate(0.0);
        }

        prevTime = currentTime;

        double limitedX = currentTranslationMag * Math.cos(currentTranslationDir);
        double limitedY = currentTranslationMag * Math.sin(currentTranslationDir);
        double limitedRotation = rotLimiter.calculate(input.getRotationSpeed());
        return new DriveInput(limitedX, limitedY, limitedRotation);
    }
}
