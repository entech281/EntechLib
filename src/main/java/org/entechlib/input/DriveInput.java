package org.entechlib.input;

/**
 * Basic outputs from a input device to be processed and sent to the drive subsystem.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class DriveInput {
    private final double x;
    private final double y;
    private final double rot;

    /**
     * Creates a new {@link DriveInput} with the given speeds as power percents up to 1.
     * 
     * 
     * @param xSpeed
     * @param ySpeed
     * @param rotationSpeed
     */
    public DriveInput(double xSpeed, double ySpeed, double rotationSpeed) {
        x = xSpeed;
        y = ySpeed;
        rot = rotationSpeed;
    }

    /**
     * @return the speed in the X direction.
     */
    public double getXSpeed() {
        return this.x;
    }

    /**
     * @return the speed in the Y direction.
     */
    public double getYSpeed() {
        return this.y;
    }

    /**
     * @return the speed of rotation.
     */
    public double getRotationSpeed() {
        return this.rot;
    }
}
