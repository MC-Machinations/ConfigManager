package me.machinemaker.configmanager.yaml;

import me.machinemaker.configmanager.configs.YamlConfig;
import me.machinemaker.configmanager.managers.YamlConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YamlCustomConfigTest {

    private final JavaPlugin javaPluginMock;

    public YamlCustomConfigTest() {
        javaPluginMock = mock(JavaPlugin.class);
        when(javaPluginMock.getDataFolder()).thenReturn(Paths.get("src", "test", "resources").toFile());
        when(javaPluginMock.getLogger()).thenReturn(Logger.getGlobal());
    }

    @Test
    public void test() {
        YamlConfigManager manager = new YamlConfigManager(javaPluginMock);
        manager.createConfig("test", "test.yml");
        YamlConfig config = manager.getConfig("test");
        config.get().set("test", true);
        manager.getConfig("test").save();
        manager.reloadConfig("test");
        assertTrue(config.get().getBoolean("test"));

    }

}
