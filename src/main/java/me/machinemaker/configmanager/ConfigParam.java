package me.machinemaker.configmanager;

import lombok.Getter;
import me.machinemaker.configmanager.annotations.Description;
import me.machinemaker.configmanager.annotations.Param;
import me.machinemaker.configmanager.annotations.Path;

import java.lang.reflect.Field;

@Getter
public class ConfigParam {

    private final Field field;
    private final String path;
    private final String description;

    public ConfigParam(Field field) {
        this.field = field;
        String path = field.getName();
        String description = null;
        if (field.isAnnotationPresent(Param.class)) {
            path = field.getAnnotation(Param.class).path();
            description = field.getAnnotation(Param.class).description();
        }
        if (field.isAnnotationPresent(Path.class))
            path = field.getAnnotation(Path.class).value();
        if (field.isAnnotationPresent(Description.class))
            description = field.getAnnotation(Description.class).value();

        this.path = path;
        this.description = description;

        if (!this.field.isAccessible()) this.field.setAccessible(true);
    }
}
