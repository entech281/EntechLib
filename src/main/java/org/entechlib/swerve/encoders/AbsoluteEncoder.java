package org.entechlib.swerve.encoders;

/**
 * Basic abstract class of an absolute encoder for a swerve module.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public abstract class AbsoluteEncoder {
    protected boolean inverted;
	protected double positionOffset;

    /**
     * Sets the starting values for the encoder.
     */
    protected AbsoluteEncoder() {
        this.inverted = false;
		this.positionOffset = 0.0;
    }

    /**
     * Returns the current raw position of the absolute encoder.
     * 
     * 
     * @return the current raw position of the absolute encoder in radians.
     */
    public abstract double getPosition();

    /**
     * Inverts the absolute encoder.
     * 
     * 
     * @param inverted flag indicating if inverted.
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    /**
     * Sets the position offset between the raw position and the virtual position.
     * 
     * 
     * @param offset offset in radians
     */
    public void setPositionOffset(double offset) {
        positionOffset = offset;
    }

    /**
     * Returns the position offset between the raw position and the virtual
     * position.
     * 
     * 
     * @return the position offset in radians.
     */
    public double getPositionOffset() {
        return positionOffset;
    }

    /**
     * Returns the virtual position of the absolute encoder (raw position minus
     * offset).
     * 
     * 
     * @return the virtual position in radians.
     */
    public double getVirtualPosition() {
        return getPosition() - positionOffset;
    }
}