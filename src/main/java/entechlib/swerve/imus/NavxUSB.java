package entechlib.swerve.imus;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;

public class NavxUSB extends NavxGyro {
    public NavxUSB(int usbPort) {
        super(new AHRS(usbPort == 1 ? Port.kUSB2 : Port.kUSB2));
    }
}
