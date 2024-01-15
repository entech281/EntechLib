package org.entechlib.swerve.imus;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Base class for navx port configurations to use the {@code SwerveIMU} interface.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public abstract class NavxGyro implements SwerveIMU {
    private final AHRS navx;

    /**
     * Creates a new {@code NavxGyro} interface from the given navx.
     * 
     * 
     * @param navx
     */
    protected NavxGyro(AHRS navx) {
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
