package entechlib.swerve;

import entechlib.swerve.config.ModuleConfig;
import entechlib.swerve.config.SwerveConfig;

public final class ConfigConstructionUtil {
    public static SwerveModule createModule(SwerveConfig swerveConfig, ModuleConfig config) {
        SwerveModule module = new SwerveModule(config.driveMotorID, config.turningMotorID, config.absoluteEncoderID,
                config.encoderOffsetRadians, config.drivingMotorInverted, config.turningMotorInverted, swerveConfig);
        return module;
    }
}
