package entechlib.input;

/**
 * A device that supplies {@link DriveInput} to be filtered and used in the swerveDrive
 * system.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public interface DriveInputDevice {
    /**
     * Creates a new {@link DriveInput} with the current inputs.
     * 
     * 
     * @return current {@link DriveInput}
     */
    public DriveInput getDriveInput();
}
