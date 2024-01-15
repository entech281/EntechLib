package entechlib.swerve.imus;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;


public class NavxMXP extends NavxGyro {
    public NavxMXP() {
        super(new AHRS(Port.kMXP));
    }
}
