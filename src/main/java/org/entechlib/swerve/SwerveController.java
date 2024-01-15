package org.entechlib.swerve;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.entechlib.input.DriveInput;
import org.entechlib.math.RateLimiter;
import org.entechlib.swerve.config.RateLimiterConfig;
import org.entechlib.swerve.config.SwerveControllerConfig;

/**
 * The {@code SwerveController} class contains the logic and odometry for the swerve system.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SwerveController {
    private SwerveDrivePoseEstimator odometry;

    private final double maxSpeedMPS;
    private final double maxSpeedRadPS;
    private final SwerveDriveKinematics kinematics;
    private final RateLimiter rateLimiter;


    /**
     * Creates a new {@code SwerveController} with the odometry in a starting position of 0, 0.
     * 
     * 
     * @param config
     * @param startingModulePositions
     * @param startingGyroAngle
     */
    public SwerveController(SwerveControllerConfig config, SwerveModulePosition[] startingModulePositions, Rotation2d startingGyroAngle) {
        maxSpeedMPS = config.getMaxSpeedMetersPerSecond();
        maxSpeedRadPS = config.getMaxAngularSpeedRadiansPerSecond();
        kinematics = config.getKinematics();
        RateLimiterConfig rateLimiterConfig = config.getRateLimiterConfig();
        rateLimiter = rateLimiterConfig != null ? new RateLimiter(rateLimiterConfig) : null;

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
     * 
     * @return The pose.
     */
    public Pose2d getPose() {
        return odometry.getEstimatedPosition();
    }

    /**
     * Generates the desired swerve module states from the given drive input and parameters.
     * 
     * 
     * @param driveInput Inputs from driver.
     * @param fieldRelative If the robot acts in relation to the field instead of its own heading.
     * @param robotAngleDegrees Current heading of the robot.
     * @return The desired states for the swerve modules.
     */
    public SwerveModuleState[] generateDriveStates(DriveInput driveInput, boolean fieldRelative, double robotAngleDegrees) {
        if (rateLimiter != null) {
            driveInput = rateLimiter.limit(driveInput);
        }

        double xSpeedDelivered = driveInput.getXSpeed() * maxSpeedMPS;
        double ySpeedDelivered = driveInput.getYSpeed() * maxSpeedMPS;
        double rotDelivered = driveInput.getRotationSpeed()
                * maxSpeedRadPS;

        if (fieldRelative) {
            return generateDriveStates(ChassisSpeeds.fromFieldRelativeSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered,
                                Rotation2d.fromDegrees(robotAngleDegrees)));
        }
        return generateDriveStates(new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));
    }

    /**
     * Generates the desired states for the robot given the desired chassis speeds.
     * 
     * 
     * @param speeds The desired speeds.
     * @return The desired swerve module states.
     */
    public SwerveModuleState[] generateDriveStates(ChassisSpeeds speeds) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(speeds);

        SwerveDriveKinematics.desaturateWheelSpeeds(
                swerveModuleStates, maxSpeedMPS);

        return swerveModuleStates;
    }
}
