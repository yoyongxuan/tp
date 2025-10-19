package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        GuiSettings guiSettingsA = new GuiSettings(1000, 1000, 500, 500);
        GuiSettings guiSettingsB = new GuiSettings(1, 2, 3, 4);
        Path addressBookFilePathA = Paths.get("testA" , "addressbook.json");
        Path addressBookFilePathB = Paths.get("testB" , "addressbook.json");
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(guiSettingsA);
        userPrefs.setAddressBookFilePath(addressBookFilePathA);

        // same object -> returns true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different types -> returns false
        assertFalse(userPrefs.equals(5));

        // same values -> returns true
        UserPrefs userPrefsDuplicate = new UserPrefs();
        userPrefsDuplicate.setGuiSettings(guiSettingsA);
        userPrefsDuplicate.setAddressBookFilePath(addressBookFilePathA);
        assertTrue(userPrefs.equals(userPrefsDuplicate));

        // different GUI settings -> returns false
        UserPrefs userPrefsDifferentGuiSettings = new UserPrefs(userPrefs);
        userPrefsDifferentGuiSettings.setGuiSettings(guiSettingsB);
        assertFalse(userPrefs.equals(userPrefsDifferentGuiSettings));

        // different address book file path -> returns false
        UserPrefs userPrefsDifferentaddressBookFilePathB = new UserPrefs(userPrefs);
        userPrefsDifferentaddressBookFilePathB.setAddressBookFilePath(addressBookFilePathB);
        assertFalse(userPrefs.equals(userPrefsDifferentaddressBookFilePathB));
    }

    @Test
    public void hashCodeMethod() {
        GuiSettings guiSettings = new GuiSettings(1000, 1000, 500, 500);
        Path addressBookFilePath = Paths.get("testA" , "addressbook.json");

        UserPrefs userPrefsA = new UserPrefs();
        userPrefsA.setGuiSettings(guiSettings);
        userPrefsA.setAddressBookFilePath(addressBookFilePath);

        UserPrefs userPrefsB = new UserPrefs();
        userPrefsB.setGuiSettings(guiSettings);
        userPrefsB.setAddressBookFilePath(addressBookFilePath);

        assertEquals(userPrefsA.hashCode(), userPrefsB.hashCode());
    }

}
