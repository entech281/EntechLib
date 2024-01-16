package org.entechlib.swerve.motors;

import org.entechlib.swerve.config.MotorConfig;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

/**
 * Swerve motor for the neo vortex motor with a spark flex control.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class SparkFlexVortex extends SparkController {
    /**
     * Creates and does basic configuration for the spark flex controller, neo vortex motor, and internal encoder.
     * 
     * 
     * @param id
     * @param config
     * @param inverted
     */
    public SparkFlexVortex(int id, MotorConfig config, boolean inverted) {
        super(new CANSparkFlex(id, MotorType.kBrushless), config, inverted);
    }
}
