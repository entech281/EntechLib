package frc.robot.commands;

import frc.robot.oi.UserPolicy;

public class DriveTwistCommand extends UserPolicyCommand {
    public DriveTwistCommand(UserPolicy policy) {
        super(policy);
    }

    @Override
    public void initialize() {
        policy.setYawFree(true);
    }

    @Override
    public void end(boolean interrupted) {
        policy.setYawFree(false);
    }
}
