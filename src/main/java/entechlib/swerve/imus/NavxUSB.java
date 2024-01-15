package entechlib.swerve.imus;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Swerve IMU for a navx connected by usb.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class NavxUSB extends NavxGyro {
    /**
     * Creates a navx on a usb port.
     * 
     * 
     * @param usbPort either 1 or 2 (anything else defaults to 1)
     */
    public NavxUSB(int usbPort) {
        super(new AHRS(usbPort == 1 ? Port.kUSB1 : (usbPort == 2 ? Port.kUSB2 : Port.kUSB)));
    }
}
