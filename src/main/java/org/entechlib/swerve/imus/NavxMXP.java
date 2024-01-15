package org.entechlib.swerve.imus;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Swerve IMU for a navx on the MXP port.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class NavxMXP extends NavxGyro {
    /**
     * Creates a new navx on the MXP port.
     */
    public NavxMXP() {
        super(new AHRS(Port.kMXP));
    }
}
