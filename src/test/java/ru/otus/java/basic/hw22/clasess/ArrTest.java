package ru.otus.java.basic.hw22.clasess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrTest {
    Arr arr;

    @BeforeEach
    public void prepare() {
        arr = new Arr();
    }

    public static Stream<Arguments> getLastOneData() {
        return Stream.of(
                Arguments.of(
                        new int[]{2},
                        new int[]{1, 2},
                        null,
                        null,
                        true
                ),
                Arguments.of(
                        new int[]{3, 2},
                        new int[]{1, 7, 1, 3, 2},
                        null,
                        null,
                        true
                ),
                Arguments.of(
                        new int[]{},
                        new int[]{1, 1},
                        null,
                        null,
                        true
                ),
                Arguments.of(
                        null,
                        new int[]{},
                        RuntimeException.class,
                        "Массив пуст",
                        false
                ),
                Arguments.of(
                        null,
                        new int[]{4, 5},
                        RuntimeException.class,
                        "В массиве нет единиц",
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getLastOneData")
    public void elementsAfterLastOne(
            int[] result,
            int[] input,
            Class<? extends RuntimeException> exception,
            String exceptionText,
            boolean shouldBeSuccessful
    ) {
        assertAll(
                () -> {
                    if (exception != null) {
                        RuntimeException ex = assertThrowsExactly(RuntimeException.class, () -> arr.elementsAfterLastOne(input));
                        assertEquals(exceptionText, ex.getMessage());
                    } else {
                        assertArrayEquals(result, arr.elementsAfterLastOne(input));
                    }
                },
                () -> {
                    if (exception != null) {
                        assertFalse(shouldBeSuccessful);
                    } else {
                        assertTrue(shouldBeSuccessful);
                    }

                }
        );
    }

    public static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2},
                        true
                ),
                Arguments.of(
                        new int[]{1, 1},
                        false
                ),
                Arguments.of(
                        new int[]{1, 3},
                        false
                ),
                Arguments.of(
                        new int[]{1, 2, 2, 1},
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void elementsFilter(
            int[] input,
            boolean shouldBeSuccessful
    ) {
        assertAll(
                () -> assertEquals(shouldBeSuccessful, arr.elementsFilter(input))
        );
    }
}