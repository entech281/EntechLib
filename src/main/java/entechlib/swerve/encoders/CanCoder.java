package entechlib.swerve.encoders;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.util.Units;

/**
 * The {@code CanCoder} class contains fields and methods pertaining to the
 * function of the absolute encoder.
 */
public class CanCoder implements AbsoluteEncoder {
	private CANcoder canCoder;
	private boolean inverted;
	private double positionOffset;

	public CanCoder(int port) {
		this.canCoder = new CANcoder(port);
		this.inverted = false;
		this.positionOffset = 0.0;
	}

	@Override
	public double getPosition() {
		return (inverted ? -1.0 : 1.0) * Units.degreesToRadians(canCoder.getAbsolutePosition().getValueAsDouble());
	}

	@Override
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	@Override
	public void setPositionOffset(double offset) {
		positionOffset = offset;
	}

	@Override
	public double getPositionOffset() {
		return positionOffset;
	}

	@Override
	public double getVirtualPosition() {
		return getPosition() - positionOffset;
	}
}