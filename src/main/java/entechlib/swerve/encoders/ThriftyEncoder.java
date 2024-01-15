// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package entechlib.swerve.encoders;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;

/**
 * The {@link ThriftyEncoder} class contains the interface for a Thrifty encoder to output on the guidelines of {@link AbsoluteEncoder}
 */
public class ThriftyEncoder extends AbsoluteEncoder {
    private AnalogInput analogInput;

    /**
     * Creates a new {@link ThriftyEncoder} on the given analog port.
     * 
     * 
     * @param port
     */
    public ThriftyEncoder(int port) {
        this.analogInput = new AnalogInput(port);
    }

    @Override
    public double getPosition() {
        return (inverted ? -1.0 : 1.0)
                * ((analogInput.getAverageVoltage() / RobotController.getVoltage5V()) * (Math.PI * 2) - Math.PI);
    }
}