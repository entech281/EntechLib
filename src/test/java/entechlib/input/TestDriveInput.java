package entechlib.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestDriveInput {
    private static final DriveInput example = new DriveInput(0.75, 0.8, 0.3);

    @Test
    void testGetRotationSpeed() {
        assertEquals(0.3, example.getRotationSpeed());
    }

    @Test
    void testGetXSpeed() {
        assertEquals(0.75, example.getXSpeed());
    }

    @Test
    void testGetYSpeed() {
        assertEquals(0.8, example.getYSpeed());
    }
}
