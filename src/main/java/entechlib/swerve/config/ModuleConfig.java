package entechlib.swerve.config;

/**
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class ModuleConfig {
    private int turningMotorID;
    private int driveMotorID;
    private int absoluteEncoderID;
    private double encoderOffsetRadians = 0;
    private boolean drivingMotorInverted = false;
    private boolean turningMotorInverted = true;

    public int getTurningMotorID() {
        return this.turningMotorID;
    }

    public int getDriveMotorID() {
        return this.driveMotorID;
    }

    public int getAbsoluteEncoderID() {
        return this.absoluteEncoderID;
    }

    public void setTurningMotorID(int turningMotorID) {
        this.turningMotorID = turningMotorID;
    }

    public void setDriveMotorID(int driveMotorID) {
        this.driveMotorID = driveMotorID;
    }

    public void setAbsoluteEncoderID(int absoluteEncoderID) {
        this.absoluteEncoderID = absoluteEncoderID;
    }

    public double getEncoderOffsetRadians() {
        return this.encoderOffsetRadians;
    }

    public void setEncoderOffsetRadians(double encoderOffsetRadians) {
        this.encoderOffsetRadians = encoderOffsetRadians;
    }

    public boolean isDrivingMotorInverted() {
        return this.drivingMotorInverted;
    }

    public boolean getDrivingMotorInverted() {
        return this.drivingMotorInverted;
    }

    public void setDrivingMotorInverted(boolean drivingMotorInverted) {
        this.drivingMotorInverted = drivingMotorInverted;
    }

    public boolean isTurningMotorInverted() {
        return this.turningMotorInverted;
    }

    public boolean getTurningMotorInverted() {
        return this.turningMotorInverted;
    }

    public void setTurningMotorInverted(boolean turningMotorInverted) {
        this.turningMotorInverted = turningMotorInverted;
    }
}