package frc.robot.oi;

public class UserPolicy {
    private boolean xLock = false;
    private boolean yawFree = false;

    public boolean isXLock() {
        return this.xLock;
    }

    public boolean getXLock() {
        return this.xLock;
    }

    public void setXLock(boolean xLock) {
        this.xLock = xLock;
    }

    public boolean isYawFree() {
        return this.yawFree;
    }

    public boolean getYawFree() {
        return this.yawFree;
    }

    public void setYawFree(boolean yawFree) {
        this.yawFree = yawFree;
    }
}
