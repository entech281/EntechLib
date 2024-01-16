package org.entechlib.swerve.imus;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Basic interface for robot IMUs that are compatible with swerve system.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public interface SwerveIMU {

    /**
     * @return The gyro angle in degrees + the user offset as continuous.
     */
    double getAngle();

    /**
     * Sets the added offset to the angle.
     * 
     * 
     * @param offset
     */
    void setAngleOffset(double offset);

    /**
     * Resets the yaw heading to 0.
     */
    void reset();

    /**
     * @return The rate of change in the yaw heading in degrees per second.
     */
    double getRate();

    /**
     * @return Meters per second in the X direction.
     */
    double getVelocityX();

    /**
     * @return Meters per second in the Y direction.
     */
    double getVelocityY();

    /**
     * @return Returns the angle as Rotation2d.
     */
    Rotation2d getRotation2d();
}