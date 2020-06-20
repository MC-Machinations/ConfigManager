package me.machinemaker.configmanager.configs;

import java.io.File;

public interface IConfig {

    boolean isSet(String path);

    Object get(String path);

    void set(String path, Object value);

    void save();
}
