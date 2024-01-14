package entechlib.swerve.config;

import com.pathplanner.lib.util.PIDConstants;

public class AutoConfig {
    private PIDConstants translationController;
    private PIDConstants rotationController;

    public PIDConstants getTranslationController() {
        return this.translationController;
    }

    public void setTranslationController(PIDConstants translationController) {
        this.translationController = translationController;
    }

    public PIDConstants getRotationController() {
        return this.rotationController;
    }

    public void setRotationController(PIDConstants rotationController) {
        this.rotationController = rotationController;
    }
}
