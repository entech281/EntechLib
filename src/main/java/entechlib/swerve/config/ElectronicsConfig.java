package entechlib.swerve.config;

import com.revrobotics.CANSparkMax.IdleMode;

public class ElectronicsConfig {
    public final ModuleConfig frontLeft, frontRight, rearLeft, rearRight;
    public final ImuConfig imuConfig;

    public final int driveMotorCurrentLimit;
    private static final int DEFAULT_DRIVE_CURRENT_LIMIT = 40;
    public final int turningMotorCurrentLimit;
    private static final int DEFAULT_TURNING_CURRENT_LIMIT = 20;

    public final IdleMode turningMotorIdleMode;
    private static final IdleMode DEFAULT_TURNING_MODE = IdleMode.kBrake;
    public final IdleMode driveMotorIdleMode;
    private static final IdleMode DEFAULT_DRIVE_MODE = IdleMode.kBrake;

    public ElectronicsConfig(ModuleConfig frontLeft, ModuleConfig frontRight, ModuleConfig rearLeft,
            ModuleConfig rearRight, ImuConfig imuConfig) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.imuConfig = imuConfig;
        turningMotorIdleMode = DEFAULT_TURNING_MODE;
        driveMotorIdleMode = DEFAULT_DRIVE_MODE;
        driveMotorCurrentLimit = DEFAULT_DRIVE_CURRENT_LIMIT;
        turningMotorCurrentLimit = DEFAULT_TURNING_CURRENT_LIMIT;
    }

    public ElectronicsConfig(ModuleConfig frontLeft, ModuleConfig frontRight, ModuleConfig rearLeft,
            ModuleConfig rearRight, ImuConfig imuConfig, int driveMotorCurrentLimit, int turningMotorCurrentLimit) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.imuConfig = imuConfig;
        turningMotorIdleMode = DEFAULT_TURNING_MODE;
        driveMotorIdleMode = DEFAULT_DRIVE_MODE;
        this.driveMotorCurrentLimit = driveMotorCurrentLimit;
        this.turningMotorCurrentLimit = turningMotorCurrentLimit;
    }

    public ElectronicsConfig(ModuleConfig frontLeft, ModuleConfig frontRight, ModuleConfig rearLeft,
            ModuleConfig rearRight, ImuConfig imuConfig, IdleMode driveMotorIdleMode, IdleMode turningMotorIdleMode) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.imuConfig = imuConfig;
        driveMotorCurrentLimit = DEFAULT_DRIVE_CURRENT_LIMIT;
        turningMotorCurrentLimit = DEFAULT_TURNING_CURRENT_LIMIT;
        this.driveMotorIdleMode = driveMotorIdleMode;
        this.turningMotorIdleMode = turningMotorIdleMode;
    }

    public ElectronicsConfig(ModuleConfig frontLeft, ModuleConfig frontRight, ModuleConfig rearLeft,
            ModuleConfig rearRight, ImuConfig imuConfig, IdleMode driveMotorIdleMode, IdleMode turningMotorIdleMode,
            int driveMotorCurrentLimit, int turningMotorCurrentLimit) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.imuConfig = imuConfig;
        this.driveMotorIdleMode = driveMotorIdleMode;
        this.turningMotorIdleMode = turningMotorIdleMode;
        this.driveMotorCurrentLimit = driveMotorCurrentLimit;
        this.turningMotorCurrentLimit = turningMotorCurrentLimit;
    }
}
