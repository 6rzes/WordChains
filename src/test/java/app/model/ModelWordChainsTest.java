package app.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ModelWordChainsTest {

    private static ModelWordChains modelWordChains;

    @BeforeAll
    static void setUp() {
        modelWordChains = new ModelWordChains();
    }

    @Test
    void testGetPathWay() {
        List<String> transitionList = List.of("iwo", "two", "owx", "twx", "twx", "two", "two", "iwo", "*&#@$#",
                ")(()*&^", "         ", "  ", "456", "AAA", "zzz");
        List<String> expectedPath = Arrays.asList("two", "twx", "owx");
        Assert.assertEquals(expectedPath, modelWordChains.getPathWay(transitionList, "two", "owx"));
    }
}