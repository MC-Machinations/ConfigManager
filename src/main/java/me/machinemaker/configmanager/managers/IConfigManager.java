package me.machinemaker.configmanager.managers;

import java.io.File;

public interface IConfigManager<T> {

    boolean createConfig(String name, String fileName);

    boolean createConfig(String name, String fileName, File directory);

    T getConfig(String name);
}
