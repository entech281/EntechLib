package frc.robot.subsystems;

import org.entechlib.input.DriveInput;
import org.entechlib.subsystems.EntechSubsystem;
import org.entechlib.swerve.SwerveDrive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.RobotConstants;

public class DriveSubsystem extends EntechSubsystem {
    private static final boolean ENABLED = true;
    private SwerveDrive swerve;

    public void setWheelsToXLock() {
        swerve.setWheelsToXLock();
    }

    public void drive(DriveInput driveInput, boolean fieldRelative) {
        swerve.drive(driveInput, fieldRelative);
    }

    public void zeroHeading() {
        swerve.zeroHeading();
    }

    public Rotation2d getHeading() {
        return swerve.getHeading();
    }

    public void addVisionData(Pose2d visionEstimatedPose, double timeStamp) {
        swerve.addVisionData(visionEstimatedPose, timeStamp);
    }

    public void resetOdometry(Pose2d pose) {
        swerve.resetOdometry(pose);
    }

    public Pose2d getPose() {
        return swerve.getPose();
    }

    @Override
    public void periodic() {
        swerve.periodic();
    }

    @Override
    public void initialize() {
        swerve = new SwerveDrive(RobotConstants.SWERVE_CONFIG, this);
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }
}
