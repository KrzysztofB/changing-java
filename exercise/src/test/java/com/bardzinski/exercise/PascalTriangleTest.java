package com.bardzinski.exercise;

import java.util.List;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.assertEquals;

//ASSERTJ OR HAMCREST
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PascalTriangleTest {

    final static Predicate<List<Long>> firstIsOne = level -> level.get(0) == 1L;

    final static Predicate<List<Long>> lastIsOne = level -> level.get(level.size() - 1) == 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(PascalTriangleTest.class);

    @Test
    void oneLevel() throws Exception {
        LOGGER.info("Started tests.");
        //given
        var triangle = new PascalTriangle();

        //when
        var result = triangle.generate(1L);

        //then
        assertEquals(1, result.size(), "triangle height should be 1");
        var level = result.get(0);
        assertEquals(1, level.size(), "first level size should be 1");
        assertEquals(1L, level.get(0));

        //assertTrue(false);
    }

    @Test
    void twoLevels() throws Exception {
        //given
        var triangle = new PascalTriangle();

        //when
        var result = triangle.generate(2L);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo(List.of(1L));
        assertThat(result.get(1)).isEqualTo(List.of(1L, 1L));
    }

    @Test
    void oneOnEachLevelSide() throws Exception {
        //given

        var triangle = new PascalTriangle();

        //when
        var result = triangle.generate(50L);

        //then
        assertThat(result).hasSize(50);
        assertThat(result.get(49)).hasSize(50);
        assertThat(result).allMatch(firstIsOne);
        assertThat(result).allMatch(lastIsOne);
    }
    
    @Test
    void level10() throws Exception {
        //given
        var triangle = new PascalTriangle();

        //when
        var result = triangle.generate(10L);

        //then
        assertThat(result.get(9)).containsExactly(1L, 9L, 36L, 84L, 126L, 126L, 84L, 36L, 9L, 1L);
    }
}
