package entechlib.swerve.imus;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;

public abstract class navxGyro implements SwerveIMU {
    private final AHRS navx;

    protected navxGyro(AHRS navx) {
        this.navx = navx;
    }

    @Override
    public double getAngle() {
        return navx.getAngle();
    }

    @Override
    public void setAngleOffset(double offset) {
        navx.setAngleAdjustment(offset);
    }

    @Override
    public void zeroYaw() {
        navx.zeroYaw();
    }

    @Override
    public void reset() {
        navx.reset();
    }

    @Override
    public double getRate() {
        return navx.getRate();
    }

    @Override
    public double getVelocityX() {
        return navx.getVelocityX();
    }

    @Override
    public double getVelocityY() {
        return navx.getVelocityY();
    }

    @Override
    public Rotation2d getRotation2d() {
        return navx.getRotation2d();
    }
}
