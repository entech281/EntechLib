package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CommandFactory {
    public Command getAutonomousCommand() {
        SequentialCommandGroup auto = new SequentialCommandGroup();
        auto.addCommands(new PathPlannerAuto("Test"));
        return auto;
    }
}
