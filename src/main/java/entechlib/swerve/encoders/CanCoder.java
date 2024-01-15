package entechlib.swerve.encoders;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.util.Units;

/**
 * The {@link CanCoder} class contains the interface for a CANCoder to output  on the guidelines of {@link AbsoluteEncoder}
 */
public class CanCoder extends AbsoluteEncoder {
	private CANcoder encoder;

	/**
	 * Creates a new {@link CanCoder} on the canbus id given.
	 * 
	 * 
	 * @param port
	 */
	public CanCoder(int port) {
		this.encoder = new CANcoder(port);
	}

	@Override
	public double getPosition() {
		return (inverted ? -1.0 : 1.0) * Units.degreesToRadians(encoder.getAbsolutePosition().getValueAsDouble());
	}
}