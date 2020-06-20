package me.machinemaker.configmanager.annotations;

import me.machinemaker.configmanager.configs.ConfigFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NewConfig {
    String name();
    String fileName() default "%%NONE%%";
    ConfigFormat format() default ConfigFormat.YAML;
}
