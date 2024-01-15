package entechlib.swerve.motors;

/**
 * Basic interface for swerve motor for turning or driving.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public interface SwerveMotor {
    /**
     * Set speed in terms of power percent.
     * 
     * 
     * @param speed As a percent of full power.
     */
    void set(double speed);

    /**
     * Resets the position of the relative encoder in rotations with the accounting of the conversion factor.
     * 
     * 
     * @param position In rotations with accounting of the conversion factor.
     */
    void setPosition(double position);

    /**
     * Returns the current position in relation to the relative encoder with the conversion factor.
     * 
     * 
     * @return Relative encoder value with the conversion factor.
     */
    double getPosition();

    /**
     * Returns the velocity of RPM in accounting of the conversion factor.
     * 
     * 
     * @return RPM with conversion factor.
     */
    double getVelocity();

    /**
     * Sets the reference in terms of either RPM or Rotations depending on control mode. Control does account for the conversion factors.
     * 
     * 
     * @param value Either RPM or Rotations with the conversion factor.
     */
    void setReference(double value);

    /**
     * @return If the motor is inverted.
     */
    boolean getInverted();
}