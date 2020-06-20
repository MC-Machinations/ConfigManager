package me.machinemaker.configmanager.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConfigFormat {
    YAML("config.yml"),

    /**
     * @deprecated Not implemented
     */
    @Deprecated
    JSON("config.json");
    String defaultFileName;
}
