package entechlib.swerve.motors;

public interface SwerveMotor {
    void setPID(double p, double i, double d, double ff);

    void setCurrentLimit(int limit);

    void completeConfigure();

    void setPositionConversionFactor(double positionConversionFactor);

    void setVelocityConversionFactor(double positionConversionFactor);

    void set(double speed);

    void setPosition(double position);

    double getPosition();

    double getVelocity();

    void setReference(double value);

    boolean getInverted();

    void setInverted(boolean inverted);
}