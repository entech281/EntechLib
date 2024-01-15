package entechlib.swerve.config;

import entechlib.math.RateLimiter;

/**
 * Configuration class for the {@link RateLimiter}
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class RateLimiterConfig {
    private double directionalSlewRate;
    private double magnitudeSlewRate;
    private double rotationalSlewRate;

    /**
     * Creates a new {@link RateLimiterConfig} with the given rate limits.
     * 
     * 
     * @param directionalSlewRate
     * @param magnitudeSlewRate
     * @param rotationalSlewRate
     */
    public RateLimiterConfig(double directionalSlewRate, double magnitudeSlewRate, double rotationalSlewRate) {
        this.directionalSlewRate = directionalSlewRate;
        this.magnitudeSlewRate = magnitudeSlewRate;
        this.rotationalSlewRate = rotationalSlewRate;
    }

    public double getDirectionalSlewRate() {
        return this.directionalSlewRate;
    }

    public void setDirectionalSlewRate(double directionalSlewRate) {
        this.directionalSlewRate = directionalSlewRate;
    }

    public double getMagnitudeSlewRate() {
        return this.magnitudeSlewRate;
    }

    public void setMagnitudeSlewRate(double magnitudeSlewRate) {
        this.magnitudeSlewRate = magnitudeSlewRate;
    }

    public double getRotationalSlewRate() {
        return this.rotationalSlewRate;
    }

    public void setRotationalSlewRate(double rotationalSlewRate) {
        this.rotationalSlewRate = rotationalSlewRate;
    }
}
