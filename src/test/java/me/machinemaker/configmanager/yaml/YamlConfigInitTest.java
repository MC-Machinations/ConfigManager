package me.machinemaker.configmanager.yaml;

import me.machinemaker.configmanager.abstracts.AbstractConfigInitTest;
import org.junit.jupiter.api.DisplayName;

@DisplayName("YAML: Initialization")
public class YamlConfigInitTest extends AbstractConfigInitTest {
    public YamlConfigInitTest() {
        super(new YamlConfigExample());
    }
}
