package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toStringMethod() {
        Config config = new Config();
        String expected = Config.class.getCanonicalName() + "{logLevel=" + config.getLogLevel()
                + ", userPrefsFilePath=" + config.getUserPrefsFilePath() + "}";
        assertEquals(expected, config.toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);

        assertFalse(defaultConfig.equals("config"));
        assertFalse(defaultConfig.equals(null));

        assertTrue(defaultConfig.equals(defaultConfig));
        assertTrue(defaultConfig.equals(new Config()));

        Config configDifferentLogLevel = new Config();
        configDifferentLogLevel.setLogLevel(Level.OFF);
        assertFalse(defaultConfig.equals(configDifferentLogLevel));

        Config configDifferentUserPrefsFilePath = new Config();
        configDifferentUserPrefsFilePath.setUserPrefsFilePath(Path.of("test"));
        assertFalse(defaultConfig.equals(configDifferentUserPrefsFilePath));
    }
    @Test
    public void hashCodeMethod() {
        Config config = new Config();
        config.setLogLevel(Level.OFF);
        config.setUserPrefsFilePath(Path.of("test"));

        assertEquals(Objects.hash(Level.OFF, Path.of("test")), config.hashCode());
    }


}
