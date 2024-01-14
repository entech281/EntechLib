package entechlib.swerve.motors;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import entechlib.swerve.config.MotorConfig;

/**
 * A swerve motor for a swerve module, either turning or driving.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SparkMaxNeo extends SparkController {
    public SparkMaxNeo(int id, MotorConfig config, boolean inverted) {
        super(new CANSparkMax(id, MotorType.kBrushless), config, inverted);
    }
}
