package me.machinemaker.configmanager.managers;

import me.machinemaker.configmanager.configs.IConfig;

import java.io.File;

public interface IConfigManager<T extends IConfig> {

    boolean createConfig(String name, String fileName);

    boolean createConfig(String name, String fileName, File directory);

    T getConfig(String name);

    void reloadConfig(String name);
}
