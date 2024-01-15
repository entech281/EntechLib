package entechlib.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/**
 * Simplified interface for command joystick.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class EntechJoystick extends CommandJoystick implements DriveInputDevice {
    private final Joystick hid;

    /**
     * Creates a new {@link EntechJoystick} on the given port number.
     * 
     * 
     * @param port
     */
    public EntechJoystick(int port) {
        super(port);
        hid = this.getHID();
    }

    /**
     * Runs the command on the given button while it is pressed. Cancels when released.
     * 
     * 
     * @param button
     * @param command
     */
    public void whilePressed(int button, Command command) {
        this.button(button).whileTrue(command);
    }

    /**
     * Runs the command once when the button is first pressed.
     * 
     * 
     * @param button
     * @param command
     */
    public void whenPressed(int button, Command command) {
        this.button(button).onTrue(command);
    }

    /**
     * Runs the command once when the button is released.
     * 
     * 
     * @param button
     * @param command
     */
    public void whenReleased(int button, Command command) {
        this.button(button).onFalse(command);
    }

    /**
     * Runs a command once when the button is released. Cancels when the button is pressed.
     * 
     * 
     * @param button
     * @param command
     */
    public void whileReleased(int button, Command command) {
        this.button(button).whileFalse(command);
    }

    /**
     * Runs the onTrue command once when the button is pressed and cancels it when released. Runs the onFalse command when the button is released and cancels the command when pressed.
     * 
     * 
     * @param button
     * @param commandOnTrue
     * @param commandOnFalse
     */
    public void whileSwitch(int button, Command commandOnTrue, Command commandOnFalse) {
        this.whilePressed(button, commandOnTrue);
        this.whileReleased(button, commandOnFalse);
    }

    /**
     * Runs the onTrue command once when the button is pressed and run the the onFalse command once when released.
     * 
     * 
     * @param button
     * @param commandOnTrue
     * @param commandOnFalse
     */
    public void whenSwitch(int button, Command commandOnTrue, Command commandOnFalse) {
        this.whenPressed(button, commandOnTrue);
        this.whenReleased(button, commandOnFalse);
    }

    @Override
    public double getX() {
        return hid.getX();
    }

    @Override
    public double getY() {
        return hid.getY();
    }

    @Override
    public double getZ() {
        return hid.getZ();
    }

    @Override
    public DriveInput getDriveInput() {
        return new DriveInput(-getY(), -getX(), -getZ());
    }
}