package me.machinemaker.configmanager.yaml;

import me.machinemaker.configmanager.abstracts.AbstractReloadTest;

public class YamlConfigReloadTest extends AbstractReloadTest {
    public YamlConfigReloadTest() {
        super(new YamlConfigExample());
    }
}
