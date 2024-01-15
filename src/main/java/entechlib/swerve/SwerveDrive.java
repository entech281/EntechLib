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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import entechlib.input.DriveInput;
import entechlib.swerve.config.SwerveConfig;

/**
 * Swerve drive system, contains the cross logic and main interface for the swerve controller and hardware.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SwerveDrive {
    private final SwerveHardware hardware;
    private final SwerveController controller;

    private final Field2d field = new Field2d();

    /**
     * Create a new swerve drive system and configures the path planner {@code AutoBuilder}.
     * 
     * 
     * @param swerveConfig config for setup
     * @param driveSubsystem drive subsystem for autonomous
     */
    public SwerveDrive(SwerveConfig swerveConfig, Subsystem driveSubsystem) {
        hardware = new SwerveHardware(swerveConfig.hardwareConfig);
        controller = new SwerveController(swerveConfig.controllerConfig, hardware.getModuleStates(), getHeading());

        AutoBuilder.configureHolonomic(
                controller::getPose, // Robot pose supplier
                this::resetOdometry, // Method to reset odometry (will be called if your auto has a starting pose)
                hardware::getChassisSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
                this::autonomousDrive,
                new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
                        swerveConfig.controllerConfig.getTranslationController(),
                        swerveConfig.controllerConfig.getRotationController(),
                        swerveConfig.controllerConfig.getMaxSpeedMetersPerSecond(),
                        swerveConfig.controllerConfig.getDriveBaseRadius(),
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

        SmartDashboard.putData(field);
    }

    /**
     * Periodic method for the swerve system. Updates odometry related systems. Needs to be ran every periodic loop.
     */
    public void periodic() {
        controller.updateOdometry(hardware.getModuleStates(), hardware.getHeading());
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

    /**
     * Helper method for autonomous driving.
     * 
     * 
     * @param speeds Desired speeds from pathplanner.
     */
    private void autonomousDrive(ChassisSpeeds speeds) {
        hardware.setModuleStates(controller.generateDriveStates(speeds));
    }
}
