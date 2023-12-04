package entechlib.swerve.config;

public class RateLimiterConfig {
    /** radians per second */
    public double directionalSlewRate = 1.2;
    /** percent per second (1 = 100%) */
    public double magnitudeSlewRate = 1.8;
    /** percent per second (1 = 100%) */
    public double rotationalSlewRate = 2.0;

    public RateLimiterConfig() {

    }

    public RateLimiterConfig(double directionalSlewRate, double magnitudeSlewRate, double rotationalSlewRate) {
        this.directionalSlewRate = directionalSlewRate;
        this.magnitudeSlewRate = magnitudeSlewRate;
        this.rotationalSlewRate = rotationalSlewRate;
    }
}
