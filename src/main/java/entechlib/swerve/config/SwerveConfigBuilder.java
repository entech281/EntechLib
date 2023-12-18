package entechlib.swerve.config;

public final class SwerveConfigBuilder {
    public RobotHardware getDefaultBuilder() {
        return new Builder();
    }

    public interface RobotHardware {
        public RateLimiting withRobotSize(double wheelBaseMeters, double trackWidthMeters);
    }

    public interface RateLimiting {
        public void withRateLimiting(RateLimiterConfig c);

        public void withoutRateLimiting();
    }

    public class Builder implements RobotHardware, RateLimiting {

        @Override
        public RateLimiting withRobotSize(double wheelBaseMeters, double trackWidthMeters) {
            // TODO Auto-generated method stub
            return this;
        }

        @Override
        public void withRateLimiting(RateLimiterConfig c) {
            // TODO Auto-generated method stub

        }

        @Override
        public void withoutRateLimiting() {
            // TODO Auto-generated method stub

        }

    }
}
