package org.entechlib.swerve.config;

import org.entechlib.swerve.SwerveController;
import org.entechlib.swerve.SwerveHardware;

/**
 * Configuration object used to build the swerve drive system.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SwerveConfig {
    /** Configuration relating to the {@link SwerveController}. */
    public final SwerveControllerConfig controllerConfig = new SwerveControllerConfig();

    /** Configuration relating to the {@link SwerveHardware}. */
    public final SwerveHardwareConfig hardwareConfig = new SwerveHardwareConfig();
}
