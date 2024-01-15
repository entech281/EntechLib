// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package entechlib.swerve.encoders;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;

/**
 * The {@code ThriftyEncoder} class contains fields and methods pertaining to
 * the function of the absolute encoder.
 */
public class ThriftyEncoder extends AbsoluteEncoder {
    private AnalogInput analogInput;

    public ThriftyEncoder(int port) {
        this.analogInput = new AnalogInput(port);
    }

    @Override
    public double getPosition() {
        return (inverted ? -1.0 : 1.0)
                * ((analogInput.getAverageVoltage() / RobotController.getVoltage5V()) * (Math.PI * 2) - Math.PI);
    }
}