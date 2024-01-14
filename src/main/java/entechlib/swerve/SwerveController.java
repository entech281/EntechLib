package entechlib.swerve;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import entechlib.input.DriveInput;
import entechlib.math.RateLimiter;
import entechlib.swerve.config.SwerveConfig;

public class SwerveController {
    private SwerveDrivePoseEstimator odometry;

    private final double maxSpeedMPS;
    private final double maxSpeedRadPS;
    private final SwerveDriveKinematics kinematics;
    private final RateLimiter rateLimiter;

    public SwerveController(SwerveConfig config, SwerveModulePosition[] startingModulePositions, Rotation2d startingGyroAngle) {
        maxSpeedMPS = config.getMaxSpeedMetersPerSecond();
        maxSpeedRadPS = config.getMaxAngularSpeedRadiansPerSecond();
        kinematics = config.getDriveKinematics();
        rateLimiter = config.isRateLimiting() ? new RateLimiter(config.getRateLimiterConfig()) : null;

        odometry = new SwerveDrivePoseEstimator(kinematics, startingGyroAngle, startingModulePositions, new Pose2d());
    }

    /**
     * Updates the odometry position using latest measurements.
     * 
     * 
     * @param positions
     * @param robotAngleDegrees
     */
    public void updateOdometry(SwerveModulePosition[] positions, double robotAngleDegrees) {
        odometry.update(
                Rotation2d.fromDegrees(robotAngleDegrees),
                positions);
    }

    /**
     * Updates the odometry position using latest pose estimate from vision.
     * 
     * 
     * @param visionEstimatedPose
     * @param timeStamp
     */
    public void addVisionData(Pose2d visionEstimatedPose, double timeStamp) {
        odometry.addVisionMeasurement(visionEstimatedPose, timeStamp);
    }

    /**
     * Resets the odometry to the specified pose.
     * 
     * 
     * @param pose
     * @param positions
     * @param robotAngleDegrees
     */
    public void resetOdometry(Pose2d pose, SwerveModulePosition[] positions, double robotAngleDegrees) {
        odometry.resetPosition(
                Rotation2d.fromDegrees(robotAngleDegrees),
                positions,
                pose);
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return odometry.getEstimatedPosition();
    }

    public SwerveModuleState[] generateDriveStates(DriveInput driveInput, boolean fieldRelative, double robotAngleDegrees) {
        if (rateLimiter != null) {
            driveInput = rateLimiter.limit(driveInput);
        }

        // Convert the commanded speeds into the correct units for the drivetrain
        double xSpeedDelivered = driveInput.getXSpeed() * maxSpeedMPS;
        double ySpeedDelivered = driveInput.getYSpeed() * maxSpeedMPS;
        double rotDelivered = driveInput.getRotationSpeed()
                * maxSpeedRadPS;

        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(
                fieldRelative
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered,
                                Rotation2d.fromDegrees(robotAngleDegrees))
                        : new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));

        SwerveDriveKinematics.desaturateWheelSpeeds(
                swerveModuleStates, maxSpeedMPS);

        return swerveModuleStates;
    }

    public SwerveModuleState[] generateDriveStates(ChassisSpeeds speeds) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(speeds);

        SwerveDriveKinematics.desaturateWheelSpeeds(
                swerveModuleStates, maxSpeedMPS);

        return swerveModuleStates;
    }
}
