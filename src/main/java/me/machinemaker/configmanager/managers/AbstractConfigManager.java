package me.machinemaker.configmanager.managers;

import me.machinemaker.configmanager.Lang;
import me.machinemaker.configmanager.configs.IConfig;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfigManager<T extends IConfig> implements IConfigManager<T> {

    protected final Map<String, T> configs;
    protected final JavaPlugin plugin;

    public AbstractConfigManager(@NotNull JavaPlugin plugin) {
        configs = new HashMap<>();
        this.plugin = plugin;
    }

    @Override
    public T getConfig(String name) {
        return this.configs.get(name);
    }

    @Override
    public void reloadConfig(String name) {
        if (this.configs.containsKey(name)) this.configs.get(name).reload();
    }

    protected void info(String msg) {
        this.plugin.getLogger().info(String.format("%s %s", Lang.PREFIX, msg));
    }

    protected void error(String msg) {
        this.plugin.getLogger().severe(String.format("%s %s", Lang.PREFIX, msg));
    }
}
