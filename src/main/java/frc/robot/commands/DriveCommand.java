package frc.robot.commands;

import org.entechlib.commands.EntechCommand;
import org.entechlib.input.DriveInput;
import org.entechlib.input.EntechJoystick;

import edu.wpi.first.math.MathUtil;
import frc.robot.RobotConstants;
import frc.robot.oi.UserPolicy;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends EntechCommand {
    private static final double MAX_SPEED_PERCENT = 1;

    private final DriveSubsystem drive;
    private final EntechJoystick joystick;
    private final UserPolicy policy;

    public DriveCommand(DriveSubsystem drive, EntechJoystick joystick, UserPolicy policy) {
        super(drive);
        this.drive = drive;
        this.joystick = joystick;
        this.policy = policy;
    }

    @Override
    public void end(boolean interrupted) {
        drive.drive(new DriveInput(0, 0, 0), true);
    }

    @Override
    public void execute() {
        double xRaw = joystick.getX();
        double yRaw = joystick.getY();
        double rotRaw = joystick.getZ();

        double xConstrained = MathUtil.applyDeadband(MathUtil.clamp(xRaw, -MAX_SPEED_PERCENT, MAX_SPEED_PERCENT),
                RobotConstants.DEADBAND);
        double yConstrained = MathUtil.applyDeadband(MathUtil.clamp(yRaw, -MAX_SPEED_PERCENT, MAX_SPEED_PERCENT),
                RobotConstants.DEADBAND);
        double rotConstrained = MathUtil.applyDeadband(
                MathUtil.clamp(rotRaw, -MAX_SPEED_PERCENT, MAX_SPEED_PERCENT),
                RobotConstants.DEADBAND);

        double xSquared = Math.copySign(xConstrained * xConstrained, xConstrained);
        double ySquared = Math.copySign(yConstrained * yConstrained, yConstrained);
        double rotSquared = Math.copySign(rotConstrained * rotConstrained, rotConstrained);

        if (policy.getXLock()) {
            drive.setWheelsToXLock();
            return;
        }

        if (policy.getYawFree()) {
            drive.drive(new DriveInput(-ySquared, -xSquared, -rotSquared), true);
        } else {
            drive.drive(new DriveInput(-ySquared, -xSquared, 0), true);
        }
    }

    @Override
    public void initialize() {
        drive.drive(new DriveInput(0, 0, 0), true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
