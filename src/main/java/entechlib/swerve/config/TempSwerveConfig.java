package entechlib.swerve.config;

public class TempSwerveConfig {
    private SwerveControllerConfig controllerConfig = new SwerveControllerConfig();
    private SwerveHardwareConfig hardwareConfig = new SwerveHardwareConfig();


    public SwerveControllerConfig getControllerConfig() {
        return this.controllerConfig;
    }

    public SwerveHardwareConfig getHardwareConfig() {
        return this.hardwareConfig;
    }
}
