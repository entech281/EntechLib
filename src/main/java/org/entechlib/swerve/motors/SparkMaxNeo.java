package org.entechlib.swerve.motors;

import org.entechlib.swerve.config.MotorConfig;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;


/**
 * Swerve motor for the neo motor with a spark max control.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SparkMaxNeo extends SparkController {
    /**
     * Creates and does configuration for the spark max control, neo motor, and internal encoder.
     * 
     * 
     * @param id
     * @param config
     * @param inverted
     */
    public SparkMaxNeo(int id, MotorConfig config, boolean inverted) {
        super(new CANSparkMax(id, MotorType.kBrushless), config, inverted);
    }
}
