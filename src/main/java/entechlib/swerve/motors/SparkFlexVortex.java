package entechlib.swerve.motors;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import entechlib.swerve.config.MotorConfig;

public class SparkFlexVortex extends SparkController {
    public SparkFlexVortex(int id, MotorConfig config, boolean inverted) {
        super(new CANSparkFlex(id, MotorType.kBrushless), config, inverted);
    }
}
