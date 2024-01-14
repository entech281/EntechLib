// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package entechlib.swerve;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Subsystem;
import entechlib.input.DriveInput;
import entechlib.swerve.config.SwerveConfig;

/**
 * The {@code Drivetrain} class contains fields and methods pertaining to the
 * function of the drivetrain.
 */
public class SwerveDrive {
    private SwerveHardware hardware;
    private SwerveController controller;

    Field2d field = new Field2d();

    public void periodic() {
        field.setRobotPose(controller.getPose());

    }

    /**
     * Method to drive the robot using joystick info.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot           Angular rate of the robot.
     * @param fieldRelative Whether the provided x and y speeds are relative to the
     *                      field.
     * @param rateLimit     Whether to enable rate limiting for smoother control.
     */
    public void drive(DriveInput driveInput, boolean fieldRelative, double robotAngleDegrees) {
        hardware.setModuleStates(controller.generateDriveStates(driveInput, fieldRelative, robotAngleDegrees));
    }
    
    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        hardware.zeroHeading();
        Pose2d pose = new Pose2d(controller.getPose().getTranslation(), Rotation2d.fromDegrees(0));
        controller.resetOdometry(pose, hardware.getModuleStates(), hardware.getHeading());
    }

    /**
     * Returns the heading of the robot as a Rotation2d.
     * 
     * 
     * @return robot heading
     */
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(hardware.getHeading());
    }

    /**
     * Add vision estimate data to the odometry.
     * 
     * 
     * @param visionEstimatedPose
     * @param timeStamp
     */
    public void addVisionData(Pose2d visionEstimatedPose, double timeStamp) {
        controller.addVisionData(visionEstimatedPose, timeStamp);
    }

    
    /**
     * Set the odometry pose.
     * 
     * 
     * @param pose
     */
    public void resetOdometry(Pose2d pose) {
        controller.resetOdometry(pose, hardware.getModuleStates(), hardware.getHeading());
    }

    /**
     * Get the latest estimated pose.
     * 
     * 
     * @return estimated pose
     */
    public Pose2d getPose() {
        return controller.getPose();
    }

    private void autonomousDrive(ChassisSpeeds speeds) {
        hardware.setModuleStates(controller.generateDriveStates(speeds));
    }

    /**
     * Create a new swerve drive system.
     * 
     * 
     * @param swerveConfig config for setup
     * @param driveSubsystem drive subsystem for autonomous
     */
    public SwerveDrive(SwerveConfig swerveConfig, Subsystem driveSubsystem) {
        hardware = new SwerveHardware(swerveConfig);
        controller = new SwerveController(swerveConfig, hardware.getModuleStates(), getHeading());

        AutoBuilder.configureHolonomic(
                controller::getPose, // Robot pose supplier
                this::resetOdometry, // Method to reset odometry (will be called if your auto has a starting pose)
                hardware::getChassisSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
                this::autonomousDrive,
                new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
                        swerveConfig.getAutoConfig().getTranslationController(),
                        swerveConfig.getAutoConfig().getRotationController(),
                        swerveConfig.getMaxSpeedMetersPerSecond(),
                        swerveConfig.getDriveBaseRadius(),
                        new ReplanningConfig()
                ),
                () -> {
                    var alliance = DriverStation.getAlliance();
                    if (alliance.isPresent()) {
                        return alliance.get() == DriverStation.Alliance.Red;
                    }
                    return false;
                },
                driveSubsystem
        );
    }
}
