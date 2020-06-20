package me.machinemaker.configmanager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Lang {
    PREFIX("[CM]"),
    NAME("ConfigManager"),
    VERSION("ALPHA");

    String txt;

    @Override
    public String toString() {
        return this.txt;
    }
}
