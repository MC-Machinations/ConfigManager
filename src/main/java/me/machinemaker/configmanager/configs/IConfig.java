package me.machinemaker.configmanager.configs;

public interface IConfig<T> {

    boolean isSet(String path);

    Object get(String path);

    void set(String path, Object value);

    void save();

    void reload();

    T get();
}
