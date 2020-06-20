package me.machinemaker.configmanager.yaml;

import me.machinemaker.configmanager.abstracts.AbstractIOTest;
import org.junit.jupiter.api.DisplayName;

@DisplayName("YAML: IO Tests")
public class YamlConfigIOTest extends AbstractIOTest {
    public YamlConfigIOTest() {
        super(new YamlConfigExample());
    }
}
