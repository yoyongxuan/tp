package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null email
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // blank telegram handle
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only

        // missing parts
        assertFalse(TelegramHandle.isValidTelegramHandle("bobby")); // missing @
        assertFalse(TelegramHandle.isValidTelegramHandle("@")); // only @

        // invalid parts
        assertFalse(TelegramHandle.isValidTelegramHandle("@-")); // username dash
        assertFalse(TelegramHandle.isValidTelegramHandle("a@a")); // nothing before @
        assertTrue(TelegramHandle.isValidTelegramHandle("@_")); // underscore allowed
        assertFalse(TelegramHandle.isValidTelegramHandle("@@")); // two @s
        assertFalse(TelegramHandle.isValidTelegramHandle("@")); // cannot be just @ symbol
        assertFalse(TelegramHandle.isValidTelegramHandle("@ ")); // space after
        assertFalse(TelegramHandle.isValidTelegramHandle(" @")); // space before
        assertFalse(TelegramHandle.isValidTelegramHandle(" @ ")); // space before and after
        assertFalse(TelegramHandle.isValidTelegramHandle("@Jotham_()")); // only alphanumeric and underscores

        // valid parts
        assertTrue(TelegramHandle.isValidTelegramHandle("@_")); // technically legit
        assertTrue(TelegramHandle.isValidTelegramHandle("@Jotham"));
        assertTrue(TelegramHandle.isValidTelegramHandle("@Jotham_"));
    }

    @Test
    public void equals() {
        TelegramHandle telegramHandle = new TelegramHandle("@Amy");

        // same values -> returns true
        assertTrue(telegramHandle.equals(new TelegramHandle("@Amy")));

        // same object -> returns true
        assertTrue(telegramHandle.equals(telegramHandle));

        // null -> returns false
        assertFalse(telegramHandle.equals(null));

        // different types -> returns false
        assertFalse(telegramHandle.equals(5.0f));

        // different values -> returns false
        assertFalse(telegramHandle.equals(new TelegramHandle("@Bob")));
    }
}
