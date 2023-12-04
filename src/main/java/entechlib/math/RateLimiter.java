package entechlib.math;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.util.WPIUtilJNI;
import entechlib.input.DriveInput;
import entechlib.swerve.SwerveUtils;
import entechlib.swerve.config.RateLimiterConfig;

public class RateLimiter {
    private final double directionalSlewRate;

    public RateLimiter(RateLimiterConfig config) {
        m_magLimiter = new SlewRateLimiter(config.magnitudeSlewRate);
        m_rotLimiter = new SlewRateLimiter(config.rotationalSlewRate);
        directionalSlewRate = config.directionalSlewRate;
    }

    private double m_currentTranslationDir = 0.0;
    private double m_currentTranslationMag = 0.0;

    private final SlewRateLimiter m_magLimiter;
    private final SlewRateLimiter m_rotLimiter;
    private double m_prevTime = WPIUtilJNI.now() * 1e-6;

    public DriveInput limit(DriveInput input) {
        // Convert XY to polar for rate limiting
        double inputTranslationDir = Math.atan2(input.getYSpeed(), input.getXSpeed());
        double inputTranslationMag = Math
                .sqrt(Math.pow(input.getXSpeed(), 2) + Math.pow(input.getYSpeed(), 2));

        // Calculate the direction slew rate based on an estimate of the lateral
        // acceleration
        double directionSlewRate;

        if (m_currentTranslationMag != 0.0) {
            directionSlewRate = Math.abs(directionalSlewRate / m_currentTranslationMag);
        } else {
            directionSlewRate = 500.0; // some high number that means the slew rate is effectively instantaneous
        }

        double currentTime = WPIUtilJNI.now() * 1e-6;
        double elapsedTime = currentTime - m_prevTime;
        double angleDif = SwerveUtils.AngleDifference(inputTranslationDir, m_currentTranslationDir);

        if (angleDif < 0.45 * Math.PI) {
            m_currentTranslationDir = SwerveUtils.StepTowardsCircular(m_currentTranslationDir,
                    inputTranslationDir,
                    directionSlewRate * elapsedTime);
            m_currentTranslationMag = m_magLimiter.calculate(inputTranslationMag);
        } else if (angleDif > 0.85 * Math.PI) {
            if (m_currentTranslationMag > 1e-4) {
                m_currentTranslationMag = m_magLimiter.calculate(0.0);
            } else {
                m_currentTranslationDir = SwerveUtils.WrapAngle(m_currentTranslationDir + Math.PI);
                m_currentTranslationMag = m_magLimiter.calculate(inputTranslationMag);
            }
        } else {
            m_currentTranslationDir = SwerveUtils.StepTowardsCircular(m_currentTranslationDir,
                    inputTranslationDir,
                    directionSlewRate * elapsedTime);
            m_currentTranslationMag = m_magLimiter.calculate(0.0);
        }

        m_prevTime = currentTime;

        double limitedX = m_currentTranslationMag * Math.cos(m_currentTranslationDir);
        double limitedY = m_currentTranslationMag * Math.sin(m_currentTranslationDir);
        double limitedRotation = m_rotLimiter.calculate(input.getRotationSpeed());
        return new DriveInput(limitedX, limitedY, limitedRotation);
    }
}
