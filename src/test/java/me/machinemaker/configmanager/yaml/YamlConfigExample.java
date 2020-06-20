package me.machinemaker.configmanager.yaml;

import me.machinemaker.configmanager.abstracts.AbstractExampleConfig;
import me.machinemaker.configmanager.annotations.NewConfig;
import me.machinemaker.configmanager.configs.ConfigFormat;

@NewConfig(format = ConfigFormat.YAML, name = "config.yml")
public class YamlConfigExample extends AbstractExampleConfig { }
