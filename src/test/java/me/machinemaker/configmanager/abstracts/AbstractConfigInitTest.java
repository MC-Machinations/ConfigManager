package me.machinemaker.configmanager.abstracts;

import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractConfigInitTest {

    protected AbstractExampleConfig config;
    private final JavaPlugin javaPluginMock;

    public AbstractConfigInitTest(AbstractExampleConfig config) {
        this.config = config;
        javaPluginMock = mock(JavaPlugin.class);
        when(javaPluginMock.getDataFolder()).thenReturn(Paths.get("src", "test", "resources").toFile());
        when(javaPluginMock.getLogger()).thenReturn(null);

        config.init(javaPluginMock);
    }

    @DisplayName("Numbers")
    @ParameterizedTest(name = "[{index}] {1}")
    @CsvFileSource(resources = "/field-names/numbers.csv", numLinesToSkip = 1)
    public void testNumbers(String input, String ignored) {
        test(input);
    }

    @DisplayName("Misc.")
    @ParameterizedTest(name = "[{index}] {1}")
    @CsvFileSource(resources = "/field-names/misc.csv", numLinesToSkip = 1)
    public void testMisc(String input, String ignored) {
        test(input);
    }

    private void test(String fieldName) {
        try {
            config.updateParam(config.getConfigParam(fieldName), false);
        } catch (ClassCastException classCastException) {
            fail(classCastException.getMessage());
        } catch (Throwable ignored) {}
    }
}
