package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void getWindowCoordinates() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertEquals(null, defaultGuiSettings.getWindowCoordinates());

        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        Point windowCoordinates = new Point(3, 4);
        assertEquals(windowCoordinates, guiSettings.getWindowCoordinates());
        guiSettings.getWindowCoordinates().move(100, 100);
        assertEquals(windowCoordinates, guiSettings.getWindowCoordinates());
    }

    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }

    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);

        // same values -> returns true
        assertTrue(guiSettings.equals(new GuiSettings(1, 2, 3, 4)));

        // same object -> returns true
        assertTrue(guiSettings.equals(guiSettings));

        // null -> returns false
        assertFalse(guiSettings.equals(null));

        // different types -> returns false
        assertFalse(guiSettings.equals(5.0f));

        // different values -> returns false
        assertFalse(guiSettings.equals(new GuiSettings(0, 2, 3, 4)));
        assertFalse(guiSettings.equals(new GuiSettings(1, 0, 3, 4)));
        assertFalse(guiSettings.equals(new GuiSettings(1, 2, 0, 4)));
        assertFalse(guiSettings.equals(new GuiSettings(1, 2, 3, 0)));
    }
}
