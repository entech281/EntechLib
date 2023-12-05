package entechlib.swerve.config;

public class ElectronicsConfig {
    public ModuleConfig frontLeft, frontRight, rearLeft, rearRight;
    public ImuConfig imuConfig;

    /** Default limit is 40 amps */
    public int driveMotorCurrentLimit = 40;
    /** Default limit is 20 amps */
    public int turningMotorCurrentLimit = 20;

    public ElectronicsConfig(ModuleConfig frontLeft, ModuleConfig frontRight, ModuleConfig rearLeft,
            ModuleConfig rearRight, ImuConfig imuConfig) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.imuConfig = imuConfig;
    }
}
