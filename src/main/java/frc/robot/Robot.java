package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.oi.OperatorInterface;
import frc.robot.subsystems.DriveSubsystem;

public class Robot extends TimedRobot {
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private CommandFactory commandFactory;
    private Command autonomous;

    @Override
    public void autonomousInit() {
        autonomous = commandFactory.getAutonomousCommand();

        if (autonomous != null) {
            autonomous.schedule();
        }
    }

    @Override
    public void robotInit() {
        driveSubsystem.initialize();

        commandFactory = new CommandFactory();
        OperatorInterface.create(driveSubsystem);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomous != null) {
            autonomous.cancel();
        }
    }
}
