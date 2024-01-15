package entechlib.swerve.encoders;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.util.Units;

/**
 * The {@code CanCoder} class contains fields and methods pertaining to the
 * function of the absolute encoder.
 */
public class CanCoder extends AbsoluteEncoder {
	private CANcoder encoder;

	public CanCoder(int port) {
		this.encoder = new CANcoder(port);
	}

	@Override
	public double getPosition() {
		return (inverted ? -1.0 : 1.0) * Units.degreesToRadians(encoder.getAbsolutePosition().getValueAsDouble());
	}
}