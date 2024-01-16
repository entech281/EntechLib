package frc.robot.commands;

import frc.robot.oi.UserPolicy;

public class DriveXLockCommand extends UserPolicyCommand {
    public DriveXLockCommand(UserPolicy policy) {
        super(policy);
    }

    @Override
    public void end(boolean interrupted) {
        policy.setXLock(false);
    }

    @Override
    public void initialize() {
        policy.setXLock(true);
    }
}
