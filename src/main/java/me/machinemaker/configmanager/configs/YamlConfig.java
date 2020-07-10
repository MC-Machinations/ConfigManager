package me.machinemaker.configmanager.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class YamlConfig implements IConfig<YamlConfiguration> {

    File configurationFile;
    YamlConfiguration configuration;

    public YamlConfig(@NotNull File file) {
        configurationFile = file;
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public boolean isSet(@NotNull String path) {
        return configuration.isSet(path);
    }

    @Override
    public Object get(@NotNull String path) {
        return configuration.get(path);
    }

    @Override
    public void set(@NotNull String path, Object value) {
        configuration.set(path, value);
    }

    @Override
    public void save() {
        try {
            configuration.save(configurationFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void reload() {
        try {
            configuration.load(configurationFile);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public YamlConfiguration get() {
        return configuration;
    }
}
