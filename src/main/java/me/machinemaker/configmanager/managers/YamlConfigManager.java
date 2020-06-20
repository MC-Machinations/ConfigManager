package me.machinemaker.configmanager.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class YamlConfigManager extends AbstractConfigManager<YamlConfiguration> {

    public YamlConfigManager(@NotNull JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean createConfig(String name, String fileName) {
        return createConfig(name, fileName, this.plugin.getDataFolder());
    }

    @Override
    public boolean createConfig(String name, String fileName, File directory) {
        File configFile = new File(directory, fileName);
        boolean createdDirs = directory.mkdirs();
        boolean createdNewFile;
        try {
            createdNewFile = configFile.createNewFile();
        } catch (IOException exception) {
            this.error(String.format("Could not create file for %s", fileName));
            return false;
        }

        return  createdDirs || createdNewFile;
    }
}
