package frc.robot.oi;

import org.entechlib.input.EntechJoystick;

import frc.robot.RobotConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveGyroReset;
import frc.robot.commands.DriveTwistCommand;
import frc.robot.commands.DriveXLockCommand;
import frc.robot.subsystems.DriveSubsystem;

public final class OperatorInterface {
    private static final EntechJoystick driveJoystick = new EntechJoystick(RobotConstants.DRIVE_JOYSTICK);

    public static void create(DriveSubsystem driveSubsystem) {
        UserPolicy policy = new UserPolicy();

        driveJoystick.whilePressed(1, new DriveTwistCommand(policy));
        driveJoystick.whilePressed(9, new DriveXLockCommand(policy));
        driveJoystick.whenPressed(11, new DriveGyroReset(driveSubsystem::zeroHeading));
        driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem, driveJoystick, policy));
    }

    private OperatorInterface() {
    }
}
