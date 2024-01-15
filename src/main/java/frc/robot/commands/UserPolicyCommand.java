package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.oi.UserPolicy;

public class UserPolicyCommand extends Command {
    protected final UserPolicy policy;

    public UserPolicyCommand(UserPolicy policy) {
        this.policy = policy;
    }
}
