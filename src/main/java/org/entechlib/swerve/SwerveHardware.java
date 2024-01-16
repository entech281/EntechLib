package org.entechlib.swerve;

import org.entechlib.swerve.config.SwerveHardwareConfig;
import org.entechlib.swerve.imus.SwerveIMU;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;

/**
 * Part of a swerve drive system, contains the swerve modules and gyro.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SwerveHardware {
    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule rearLeft;
    private final SwerveModule rearRight;

    private SwerveIMU gyro;

    private final boolean gyroInverted;
    private final double gyroOffset;

    public SwerveHardware(SwerveHardwareConfig config) {
        frontLeft = new SwerveModule(config, config.frontLeftConfig);
        frontRight = new SwerveModule(config, config.frontRightConfig);
        rearLeft = new SwerveModule(config, config.rearLeftConfig);
        rearRight = new SwerveModule(config, config.rearRightConfig);

        gyro = ConfigConstructionUtil.getSwerveIMU(config);


        gyroInverted = config.getGyroInverted();
        gyroOffset = config.getGyroOffset();

        zeroHeading();
        resetEncoders();
    }

    /**
     * Gets the current position of the modules.
     * 
     * 
     * @return
     */
    public SwerveModulePosition[] getModuleStates() {
        return new SwerveModulePosition[] {
                        frontLeft.getPosition(),
                        frontRight.getPosition(),
                        rearLeft.getPosition(),
                        rearRight.getPosition()
                };
    }

    /**
     * Gets the gyro heading.
     * 
     * 
     * @return angle in degrees
     */
    public double getGyroAngle() {
        return gyro.getAngle();
    }

    /**
     * Resets the drive encoders to currently read a position of 0 and seeds the
     * turn encoders using the absolute encoders.
     */
    public void resetEncoders() {
        frontLeft.resetEncoders();
        rearLeft.resetEncoders();
        frontRight.resetEncoders();
        rearRight.resetEncoders();
    }

    /**
     * Set each modules desired state in the following order: front left, front right, rear left, rear right.
     * 
     * 
     * @param states The desired module states in
     */
    public void setModuleStates(SwerveModuleState[] states) {
        frontLeft.setDesiredState(states[0]);
        frontRight.setDesiredState(states[1]);
        rearLeft.setDesiredState(states[2]);
        rearRight.setDesiredState(states[3]);
    }

    /**
     * Sets the wheels into an X formation to prevent movement.
     */
    public void lockX() {
        setModuleStates(new SwerveModuleState[] {
            new SwerveModuleState(0, Rotation2d.fromDegrees(45)),
            new SwerveModuleState(0, Rotation2d.fromDegrees(-45)),
            new SwerveModuleState(0, Rotation2d.fromDegrees(-45)),
            new SwerveModuleState(0, Rotation2d.fromDegrees(45))
        });
    }

    /**
     * Returns the heading of the robot.
     * 
     * 
     * @return the robot's heading in degrees, from -180 to 180
     */
    public Double getHeading() {
        return Rotation2d.fromDegrees(gyroInverted ? -1 : 1 * getGyroAngle()).getDegrees();
    }

    /**
     * Resets the heading of the gyro then resets the offset.
     */
    public void zeroHeading() {
        gyro.reset();
        gyro.setAngleOffset(gyroOffset);
    }

    /**
     * Returns the turn rate of the robot.
     * 
     * 
     * @return The turn rate of the robot, in degrees per second
     */
    public Double getTurnRate() {
        return gyro.getRate() * (gyroInverted ? -1.0 : 1.0);
    }

    /**
     * Generates the chassis speeds
     * 
     * 
     * @return speeds of the robot currently
     */
    public ChassisSpeeds getChassisSpeeds() {
        double radiansPerSecond = Units.degreesToRadians(gyro.getRate());
        return ChassisSpeeds.fromRobotRelativeSpeeds(gyro.getVelocityX(), gyro.getVelocityY(), radiansPerSecond, gyro.getRotation2d());
    }
}
