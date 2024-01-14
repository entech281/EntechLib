package entechlib.swerve.imus;

import edu.wpi.first.math.geometry.Rotation2d;

public interface SwerveIMU {

    double getAngle();

    void setAngleOffset(double offset);

    void zeroYaw();

    void reset();

    double getRate();

    double getVelocityX();

    double getVelocityY();

    Rotation2d getRotation2d();
}