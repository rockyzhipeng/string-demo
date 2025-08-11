package com.example.demo.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCommonUtilTest {

    @Test
    void findLongestConsecutiveStr_WithNullInput_ShouldReturnEmpty() {
        String result = StringCommonUtil.findLongestConsecutiveStr(null);
        assertThat(result).isEmpty();
    }

    @Test
    void findLongestConsecutiveStr_WithLengthLessThan3_ShouldReturnEmpty() {
        assertThat(StringCommonUtil.findLongestConsecutiveStr("")).isEmpty();
        assertThat(StringCommonUtil.findLongestConsecutiveStr("a")).isEmpty();
        assertThat(StringCommonUtil.findLongestConsecutiveStr("aa")).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideConsecutiveStrings")
    void findLongestConsecutiveStr_WithValidInput_ShouldReturnCorrectResult(String input, String expected) {
        String result = StringCommonUtil.findLongestConsecutiveStr(input);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideConsecutiveStrings() {
        return Stream.of(
                Arguments.of("aaabbb", "aaa"),
                Arguments.of("aabbbcc", "bbb"),
                Arguments.of("abcdddeee", "ddd"),
                Arguments.of("aaaa", "aaaa"),
                Arguments.of("abccba", ""),
                Arguments.of("xxxyyyzzz", "xxx"),
                Arguments.of("aabbccddeeff", "")
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"b", "c", "d", "z"})
    void getPreLetter_WithLowerCaseLetters_ShouldReturnPreviousLetter(String input) {
        char expectedChar = (char) (input.charAt(0) - 1);
        String result = StringCommonUtil.getPreLetter(input);
        assertThat(result).isEqualTo(String.valueOf(expectedChar));
    }


    @ParameterizedTest
    @ValueSource(strings = {"B", "C", "D", "Z"})
    void getPreLetter_WithUpperCaseLetters_ShouldReturnLowercasePrevious(String input) {
        char expectedChar = (char) (Character.toLowerCase(input.charAt(0)) - 1);
        String result = StringCommonUtil.getPreLetter(input);
        assertThat(result).isEqualTo(String.valueOf(expectedChar));
    }


    @ParameterizedTest
    @ValueSource(strings = {"a", "A"})
    void getPreLetter_WithLetterA_ShouldReturnEmpty(String input) {
        String result = StringCommonUtil.getPreLetter(input);
        assertThat(result).isEmpty();
    }

    // 测试非字母输入（应抛出RuntimeException）
    @ParameterizedTest
    @ValueSource(strings = {
            "1", "2", "3",
            " ", "\t", "\n",
            "#", "$", "%"
    })
    void getPreLetter_WithNonLetterInput_ShouldThrowException(String input) {

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                StringCommonUtil.getPreLetter(input));
        assertThat(exception.getMessage()).isEqualTo("The input is not an English letter!");

    }


    @Test
    void getPreLetter_WithBoundaryLetters() {
        assertThat(StringCommonUtil.getPreLetter("b")).isEqualTo("a");
        assertThat(StringCommonUtil.getPreLetter("B")).isEqualTo("a");
        assertThat(StringCommonUtil.getPreLetter("z")).isEqualTo("y");
        assertThat(StringCommonUtil.getPreLetter("Z")).isEqualTo("y");
    }
}
