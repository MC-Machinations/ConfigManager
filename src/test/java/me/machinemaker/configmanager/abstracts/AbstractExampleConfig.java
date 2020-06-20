package me.machinemaker.configmanager.abstracts;

import me.machinemaker.configmanager.BaseConfig;
import me.machinemaker.configmanager.annotations.Description;
import me.machinemaker.configmanager.annotations.Path;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractExampleConfig extends BaseConfig {
    @Description("Test Description")
    public String testString = "This is a test string";

    @Path("number.integer")
    public Integer testInt = 34;

    @Path("number.short")
    public Short testShort = 4;

    @Path("number.float")
    public Float testFloat = 3.2f;

    @Path("number.double")
    public Double testDouble = 1.4;

    @Path("number.long")
    public Long testLong = 3443L;

    public Boolean testBoolean = false;

    public Character testChar = 'T';

    @Path("list.string")
    public List<String> testStringList = Arrays.asList("test1", "test2", "test3");

    @Path("list.boolean")
    public List<Boolean> testBooleanList = Arrays.asList(true, true, false, true);

    @Path("list.char")
    public List<Character> testCharList = Arrays.asList('A', 'B', 'D', 'Z');

    @Path("list.number.integer")
    public List<Integer> testIntList = Arrays.asList(2, 5, 12, 33);

    @Path("list.number.short")
    public List<Short> testShortList = Arrays.asList((short) 3, (short) 4, (short) 12, (short) -3);

    @Path("list.number.float")
    public List<Float> testFloatList = Arrays.asList((float) 3.2, (float) 5.3, (float) -33.2);

    @Path("list.number.double")
    public List<Double> testDoubleList = Arrays.asList(-32.88, 4.3, 2.9);

    @Path("list.number.long")
    public List<Long> testLongList = Arrays.asList(400L, 33L, 323441431423527627L);
}
