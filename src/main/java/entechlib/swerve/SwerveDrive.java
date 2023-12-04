// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package entechlib.swerve;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import entechlib.LibraryConstants;
import entechlib.input.DriveInput;
import entechlib.math.RateLimiter;
import entechlib.swerve.config.SwerveConfig;

/**
 * The {@code Drivetrain} class contains fields and methods pertaining to the
 * function of the drivetrain.
 */
public class SwerveDrive {
    private final SwerveConfig swerveConfig;

    public static final int GYRO_ORIENTATION = 1; // might be able to merge with kGyroReversed
    private RateLimiter rateLimiter;

    private SwerveModule frontLeft, frontRight, rearLeft, rearRight;

    private AHRS gyro;

    private SwerveDriveOdometry odometry;

    Field2d field = new Field2d();
    // Bad look on scope needs to be fixed
    // public Pose3d createPose3d() {
    // Pose2d initial = m_odometry.getPoseMeters();

    // return new Pose3d(new Translation3d(initial.getX(), initial.getY(), 0.0),
    // new Rotation3d(Units.degreesToRadians(m_gyro.getRoll()),
    // Units.degreesToRadians(m_gyro.getPitch()),
    // Units.degreesToRadians(m_gyro.getYaw() * -1)));
    // }

    private double getGyroAngle() {
        return gyro.getAngle() + 0;
    }

    public void periodic() {
        field.setRobotPose(odometry.getPoseMeters());
        // SmartDashboard.putData("Odometry Pose Field", field);

        // SmartDashboard.putNumberArray("modules pose angles", new double[] {
        // m_frontLeft.getPosition().angle.getDegrees(),
        // m_frontRight.getPosition().angle.getDegrees(),
        // m_rearLeft.getPosition().angle.getDegrees(),
        // m_rearRight.getPosition().angle.getDegrees()
        // });
        // SmartDashboard.putNumberArray("modules pose meters", new double[] {
        // m_frontLeft.getPosition().distanceMeters,
        // m_frontRight.getPosition().distanceMeters,
        // m_rearLeft.getPosition().distanceMeters,
        // m_rearRight.getPosition().distanceMeters
        // });

        // SmartDashboard.putNumberArray("Virtual abs encoders", new double[] {
        // m_frontLeft.getTurningAbsoluteEncoder().getVirtualPosition(),
        // m_frontRight.getTurningAbsoluteEncoder().getVirtualPosition(),
        // m_rearLeft.getTurningAbsoluteEncoder().getVirtualPosition(),
        // m_rearRight.getTurningAbsoluteEncoder().getVirtualPosition()
        // });
        // SmartDashboard.putNumberArray("Raw abs encoders", new double[] {
        // m_frontLeft.getTurningAbsoluteEncoder().getPosition(),
        // m_frontRight.getTurningAbsoluteEncoder().getPosition(),
        // m_rearLeft.getTurningAbsoluteEncoder().getPosition(),
        // m_rearRight.getTurningAbsoluteEncoder().getPosition()
        // });

        // SmartDashboard.putData("NAVX", m_gyro);

        // Update the odometry in the periodic block
        odometry.update(
                Rotation2d.fromDegrees(GYRO_ORIENTATION * gyro.getAngle()),
                new SwerveModulePosition[] {
                        frontLeft.getPosition(),
                        frontRight.getPosition(),
                        rearLeft.getPosition(),
                        rearRight.getPosition()
                });
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        odometry.resetPosition(
                Rotation2d.fromDegrees(GYRO_ORIENTATION * gyro.getAngle()),
                new SwerveModulePosition[] {
                        frontLeft.getPosition(),
                        frontRight.getPosition(),
                        rearLeft.getPosition(),
                        rearRight.getPosition()
                },
                pose);
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
    public void drive(DriveInput driveInput, boolean fieldRelative) {
        if (swerveConfig.isRateLimiting()) {
            driveInput = rateLimiter.limit(driveInput);
        }

        // Convert the commanded speeds into the correct units for the drivetrain
        double xSpeedDelivered = driveInput.getXSpeed() * swerveConfig.hardwareConfig.maxSpeedMetersPerSecond;
        double ySpeedDelivered = driveInput.getYSpeed() * swerveConfig.hardwareConfig.maxSpeedMetersPerSecond;
        double rotDelivered = driveInput.getRotationSpeed()
                * swerveConfig.hardwareConfig.maxAngularSpeedRadiansPerSecond;

        var swerveModuleStates = swerveConfig.hardwareConfig.driveKinematics.toSwerveModuleStates(
                fieldRelative
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered,
                                Rotation2d.fromDegrees(GYRO_ORIENTATION * getGyroAngle()))
                        : new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));

        SwerveDriveKinematics.desaturateWheelSpeeds(
                swerveModuleStates, swerveConfig.hardwareConfig.maxSpeedMetersPerSecond);

        frontLeft.setDesiredState(swerveModuleStates[0]);
        frontRight.setDesiredState(swerveModuleStates[1]);
        rearLeft.setDesiredState(swerveModuleStates[2]);
        rearRight.setDesiredState(swerveModuleStates[3]);
    }

    /**
     * Sets the wheels into an X formation to prevent movement.
     */
    public void lockX() {
        frontLeft.setDesiredState(new SwerveModuleState(0,
                Rotation2d.fromDegrees(45)));
        frontRight.setDesiredState(new SwerveModuleState(0,
                Rotation2d.fromDegrees(-45)));
        rearLeft.setDesiredState(new SwerveModuleState(0,
                Rotation2d.fromDegrees(-45)));
        rearRight.setDesiredState(new SwerveModuleState(0,
                Rotation2d.fromDegrees(45)));
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

    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        gyro.reset();
        gyro.setAngleAdjustment(180);
        Pose2d pose = getPose();
        Pose2d pose2 = new Pose2d(pose.getTranslation(), Rotation2d.fromDegrees(0));
        resetOdometry(pose2);
    }

    public void calibrateGyro() {
        gyro.calibrate();
        while (!gyro.isCalibrating()) {
            ;
        }
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from -180 to 180
     */
    public Double getHeading() {
        return Rotation2d.fromDegrees(GYRO_ORIENTATION * getGyroAngle()).getDegrees();
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public Double getTurnRate() {
        return gyro.getRate() * (swerveConfig.electronicsConfig.imuConfig.reversed ? -1.0 : 1.0);
    }

    public SwerveDrive(SwerveConfig swerveConfig) {
        this.swerveConfig = swerveConfig;

        frontLeft = ConfigConstructionUtil.createModule(swerveConfig, swerveConfig.electronicsConfig.frontLeft);
        frontRight = ConfigConstructionUtil.createModule(swerveConfig, swerveConfig.electronicsConfig.frontRight);
        rearLeft = ConfigConstructionUtil.createModule(swerveConfig, swerveConfig.electronicsConfig.rearLeft);
        rearRight = ConfigConstructionUtil.createModule(swerveConfig, swerveConfig.electronicsConfig.rearRight);

        gyro = new AHRS(Port.kMXP);
        // calibrateGyro();
        gyro.reset();
        gyro.zeroYaw();
        if (swerveConfig.isRateLimiting()) {
            rateLimiter = new RateLimiter(swerveConfig.getRateLimiterConfig());
        }

        odometry = new SwerveDriveOdometry(
                swerveConfig.hardwareConfig.driveKinematics,
                Rotation2d.fromDegrees(GYRO_ORIENTATION * gyro.getAngle()),
                new SwerveModulePosition[] {
                        frontLeft.getPosition(),
                        frontRight.getPosition(),
                        rearLeft.getPosition(),
                        rearRight.getPosition()
                });

        resetEncoders();
        zeroHeading();

        Translation2d initialTranslation = new Translation2d(
                Units.inchesToMeters(LibraryConstants.FIELD.FIELD_LENGTH_INCHES / 2),
                Units.inchesToMeters(LibraryConstants.FIELD.FIELD_WIDTH_INCHES / 2)); // mid field
        Rotation2d initialRotation = Rotation2d.fromDegrees(180);
        gyro.setAngleAdjustment(0);
        Pose2d initialPose = new Pose2d(initialTranslation, initialRotation);
        resetOdometry(initialPose);
    }
}
