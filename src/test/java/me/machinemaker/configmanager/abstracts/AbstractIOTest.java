package me.machinemaker.configmanager.abstracts;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.mockito.Mockito;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractIOTest {

    private final Random rand;
    protected AbstractExampleConfig config;
    protected final JavaPlugin javaPluginMock;


    public AbstractIOTest(AbstractExampleConfig config) {
        this.config = config;
        this.rand = new Random();
        this.javaPluginMock = mock(JavaPlugin.class, Mockito.CALLS_REAL_METHODS);
        when(javaPluginMock.getDataFolder()).thenReturn(Paths.get("src", "test", "resources").toFile());
        when(javaPluginMock.getLogger()).thenReturn(null);

        config.init(this.javaPluginMock);
    }

    @Nested
    @DisplayName("Number Types")
    public class YamlNumbers {
        @DisplayName("Integer")
        @RepeatedTest(5)
        public void testInteger() {
            Integer randInt = rand.nextInt();
            config.testInt = randInt;
            saveAndReload();
            assertEquals(randInt, config.testInt);
        }

        @DisplayName("Short")
        @RepeatedTest(5)
        public void testShort() {
            Short randShort = (short) rand.nextInt(1 << 16);
            config.testShort = randShort;
            saveAndReload();
            assertEquals(randShort, config.testShort);
        }

        @DisplayName("Float")
        @RepeatedTest(5)
        public void testFloat() {
            Float randFloat = rand.nextFloat();
            config.testFloat = randFloat;
            saveAndReload();
            assertEquals(randFloat, config.testFloat);
        }

        @DisplayName("Double")
        @RepeatedTest(5)
        public void testDouble() {
            Double randDouble = rand.nextDouble();
            config.testDouble = randDouble;
            saveAndReload();
            assertEquals(randDouble, config.testDouble);
        }

        @DisplayName("Long")
        @RepeatedTest(5)
        public void testLong() {
            Long randLong = rand.nextLong();
            config.testLong = randLong;
            saveAndReload();
            assertEquals(randLong, config.testLong);
        }
    }

    @DisplayName("String")
    @RepeatedTest(5)
    public void testString() {
        String randString = RandomStringUtils.randomPrint(10);
        config.testString = randString;
        saveAndReload();
        assertEquals(randString, config.testString);
    }

    @DisplayName("Boolean")
    @RepeatedTest(5)
    public void testBoolean() {
        Boolean randBool = rand.nextBoolean();
        config.testBoolean = randBool;
        saveAndReload();
        assertEquals(randBool, config.testBoolean);
    }

    @DisplayName("Character")
    @RepeatedTest(5)
    public void testChar() {
        Character randChar = RandomStringUtils.randomPrint(1).charAt(0);
        config.testChar = randChar;
        saveAndReload();
        assertEquals(randChar, config.testChar);
    }

    @Nested
    @DisplayName("Lists")
    public class YamlLists {

        @DisplayName("String List")
        @RepeatedTest(5)
        public void testListString() {
            List<String> randListString = Arrays.asList(RandomStringUtils.randomPrint(30).split("(?<=\\G.{10})"));
            config.testStringList = randListString;
            saveAndReload();
            assertEquals(randListString, config.testStringList);
        }

        @DisplayName("Boolean List")
        @RepeatedTest(5)
        public void testListBoolean() {
            List<Boolean> randListBoolean = Arrays.asList(rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean());
            config.testBooleanList = randListBoolean;
            saveAndReload();
            assertEquals(randListBoolean, config.testBooleanList);
        }

        @DisplayName("Character List")
        @RepeatedTest(5)
        public void testListChar() {
            ArrayList<Character> randListChar = Lists.newArrayList();
            for (String s : RandomStringUtils.randomPrint(10).split("(?<=\\G.)")) {
                randListChar.add(s.charAt(0));
            }
//            List<Character> randListChar = Lists.newArrayList(Arrays.stream(RandomStringUtils.randomPrint(10).split("(?<=\\G.)")).map(s -> s.charAt(0)).collect(Collectors.toList()));
            config.testCharList = randListChar;
            saveAndReload();
//            assertEquals(randListChar, config.testCharList);
//            assertEquals(randListChar, config.testCharList);
        }

        @Nested
        @DisplayName("Number Lists")
        public class NumberLists {

            @DisplayName("Integer Lists")
            @RepeatedTest(5)
            public void testListInteger() {
                List<Integer> randListInt = Arrays.asList(rand.nextInt(), rand.nextInt(), rand.nextInt(), rand.nextInt(), rand.nextInt());
                config.testIntList = randListInt;
                saveAndReload();
                assertEquals(randListInt, config.testIntList);
            }

            @DisplayName("Short Lists")
            @RepeatedTest(5)
            public void testListShort() {
                List<Short> randListShort = Arrays.asList((short) rand.nextInt(1 << 16), (short) rand.nextInt(1 << 16), (short) rand.nextInt(1 << 16), (short) rand.nextInt(1 << 16));
                config.testShortList = randListShort;
                saveAndReload();
//                assertEquals(randListShort, config.testShortList);
            }
        }
    }

    private void saveAndReload() {
        this.config.save();
        this.config.reload();
    }
}
