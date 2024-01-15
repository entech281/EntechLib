package org.entechlib.swerve;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSwerveUtils {
    @Test
    void testWrapAngle() {
        assertEquals(7 - (2.0*Math.PI), SwerveUtils.wrapAngle(7));
    }

    @Test
    void testAngleDifference() {
        assertEquals(0.5, SwerveUtils.angleDifference(1.5, 1));
    }
}
