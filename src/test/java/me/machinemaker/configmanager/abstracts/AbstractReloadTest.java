package me.machinemaker.configmanager.abstracts;

import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractReloadTest {

    protected AbstractExampleConfig config;
    private final JavaPlugin javaPluginMock;

    public AbstractReloadTest(AbstractExampleConfig config) {
        this.config = config;
        javaPluginMock = mock(JavaPlugin.class);
        when(javaPluginMock.getDataFolder()).thenReturn(Paths.get("src", "test", "resources").toFile());
        when(javaPluginMock.getLogger()).thenReturn(null);

        config.init(javaPluginMock);
    }

    @Test
    public void testReload() {
        config.testBoolean = true;
        config.save();
        config.reload();
        assertTrue("failed to set to true", config.testBoolean);
        config.testBoolean = false;
        config.save();
        config.reload();
        assertFalse("failed to set to false", config.testBoolean);
    }
}
