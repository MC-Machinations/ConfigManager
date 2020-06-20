package me.machinemaker.configmanager;

import lombok.Getter;
import me.machinemaker.configmanager.annotations.NewConfig;
import me.machinemaker.configmanager.configs.IConfig;
import me.machinemaker.configmanager.configs.YamlConfig;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

public abstract class BaseConfig {

    @Getter
    private File file;
    private Logger pluginLogger;
    private IConfig configInstance;

    private final Map<String, ConfigParam> configParamMap;

    private boolean doLogging = true;

    public BaseConfig() {
        configParamMap = new HashMap<>();
    }

    /**
     * Initialize the Configuration Manager
     * @param plugin Main instance of your {@link JavaPlugin}
     */
    public void init(@NotNull JavaPlugin plugin) {
        this.init(plugin, true);
    }

    protected void init(@NotNull JavaPlugin plugin, boolean initializeFields) {
        //noinspection ConstantConditions
        if (isNull(plugin.getLogger())) {
            doLogging = false;
        }
        this.pluginLogger = plugin.getLogger();
        NewConfig configSettings;

        this.info(String.format("Initializing configuration files using %s v%s", Lang.NAME, Lang.VERSION));
        if (!this.getClass().isAnnotationPresent(NewConfig.class)) {
            this.error(String.format("%s is missing the @Config annotation!", this.getClass().getSimpleName()));
            return;
        }
        configSettings = this.getClass().getAnnotation(NewConfig.class);

        String fileName = configSettings.fileName().equals("%%NONE%%") ? configSettings.format().getDefaultFileName() : configSettings.fileName();
        this.file = new File(plugin.getDataFolder(), fileName);
        try {
            if (this.file.getParentFile().mkdirs())
                this.info("Created directory for plugin!");
            if (this.file.createNewFile())
                this.info(String.format("Created %s!", fileName));
        } catch (IOException exception) {
            this.error(String.format("Could not create %s", fileName));

            exception.printStackTrace();
        }

        switch (configSettings.format()) {
            case YAML:
                this.configInstance = new YamlConfig(this.file);
                break;
        }

        // Check for abstract classes (mainly for tests)
        Field[] fields;
        Class<?> lastClass = this.getClass();
        while (lastClass.getDeclaredFields().length == 0) {
            lastClass = lastClass.getSuperclass();
        }
        fields = lastClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isSynthetic()) continue;
            configParamMap.put(field.getName(), new ConfigParam(field));
        }

        if (initializeFields) this.reload(true);
    }

    /**
     * Saves the configuration to the file
     */
    public void save() {
        for (ConfigParam param : this.configParamMap.values()) {
            try {
                this.configInstance.set(param.getPath(), param.getField().getType().cast(param.getField().get(this)));
            } catch (IllegalAccessException illegalAccessException) {
                this.error(String.format("Unable to save %s to file!", param.getPath()));
            }
        }
        this.configInstance.save();
    }

    /**
     * Reloads the configuration (this adds entries for any that may have been deleted)
     */
    public void reload() {
        this.reload(false);
    }

    private void reload(boolean firstLoad) {
        for (ConfigParam param : this.configParamMap.values()) {
            try {
                updateParam(param, firstLoad);
            } catch (IllegalAccessException illegalAccessException) {
                this.error(String.format("Error loading configuration value for %s, please change its access modifier to public if it is not already", param.getPath()));
                illegalAccessException.printStackTrace();
            } catch (ClassCastException classCastException) {
                this.error(String.format("Error loading configuration value for %s. %s.", param.getPath(), classCastException.getMessage()));
            }
        }
    }

    public void updateParam(@NotNull ConfigParam param, boolean firstLoad) throws IllegalAccessException, ClassCastException {
        Field field = param.getField();
        String path = param.getPath();
        if (!this.configInstance.isSet(path)) {
            this.configInstance.set(path, field.getType().cast(field.get(this)));
            this.configInstance.save();

        } else {
            if (!this.configInstance.isSet(path)) {
                this.configInstance.set(path, field.getType().cast(field.get(this)));
                this.configInstance.save();
                if (!firstLoad) {
                    this.info(String.format("Did not find value for %s, resetting to default", path));
                }
            } else {
                if (this.configInstance.get(path).getClass() == field.getType()) {
                    field.set(this, field.getType().cast(this.configInstance.get(path)));
                    return;
                }
                switch (field.getType().getSimpleName()) {
                    case "Short":
                        field.set(this, ((Integer) this.configInstance.get(path)).shortValue());
                        break;
                    case "Float":
                        field.set(this, ((Double) this.configInstance.get(path)).floatValue());
                        break;
                    case "Long": // configInstance.get() returns an integer if the number is small enough to be a integer.
                        field.set(this, ((Integer) this.configInstance.get(path)).longValue());
                        break;
                    case "Character":
                        field.set(this, ((String) this.configInstance.get(path)).charAt(0));
                        break;
                    default:
                        field.set(this, field.getType().cast(this.configInstance.get(path)));
                }
            }
        }
    }

    public ConfigParam getConfigParam(String fieldName) {
        return this.configParamMap.get(fieldName);
    }

    private void info(String msg) {
        if (doLogging) this.pluginLogger.info(String.format("%s %s", Lang.PREFIX, msg));
    }

    private void error(String msg) {
        if (doLogging) this.pluginLogger.severe(String.format("%s %s", Lang.PREFIX, msg));
    }
}
