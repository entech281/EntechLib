package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveGyroReset extends Command {
    private final Runnable resetMethod;

    public DriveGyroReset(Runnable resetMethod) {
        this.resetMethod = resetMethod;
    }

    @Override
    public void initialize() {
        resetMethod.run();
    }
}
