package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isValidTagName_validTagNames_returnsTrue() {
        // alphanumeric tag names
        assertTrue(Tag.isValidTagName("friend"));
        assertTrue(Tag.isValidTagName("colleague123"));
        assertTrue(Tag.isValidTagName("best friend"));
        assertTrue(Tag.isValidTagName("a1 b2 c3"));

        // long tag name
        assertTrue(Tag.isValidTagName("beepboopbingbong with spaces 123456 r a   nd o m"));
    }

    @Test
    public void isValidTagName_invalidTagNames_returnsFalse() {
        // empty tag name
        assertFalse(Tag.isValidTagName(""));

        // spaces only
        assertFalse(Tag.isValidTagName("     "));

        // non-alphanumeric characters
        assertFalse(Tag.isValidTagName("friend!"));
        assertFalse(Tag.isValidTagName("colleague@123"));
        assertFalse(Tag.isValidTagName("best#friend"));
        assertFalse(Tag.isValidTagName("hello_world"));
        assertFalse(Tag.isValidTagName("tag-with-dash"));
    }

    @Test
    public void equals() {
        Tag tag = new Tag("test");

        // same values -> returns true
        assertTrue(tag.equals(new Tag("test")));

        // same object -> returns true
        assertTrue(tag.equals(tag));

        // null -> returns false
        assertFalse(tag.equals(null));

        // different types -> returns false
        assertFalse(tag.equals(5.0f));

        // different values -> returns false
        assertFalse(tag.equals(new Tag("test2")));
    }

}
