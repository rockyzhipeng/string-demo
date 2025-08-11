package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringCommonUtil {

    public static String findLongestConsecutiveStr(final String input) {
        if (input == null || input.length() < 3) {
            return "";
        }

        int i = 0;
        while (i < input.length()) {
            char current = input.charAt(i);
            int j = i;

            // Find the end position of consecutive identical characters
            while (j < input.length() && input.charAt(j) == current) {
                j++;
            }

            // Calculate the length
            int length = j - i;

            // If the length is greater than or equal to 3, return the sequence
            if (length >= 3) {
                return String.valueOf(current).repeat(length);
            }

            // move to next char
            i = j;
        }

        return "";
    }

    public static String getPreLetter(final String letter) {
        char ch = letter.charAt(0);

        if (!Character.isLetter(ch)) {
            log.info("The input is not an English letter!");
            throw new RuntimeException("The input is not an English letter!");
        }

        ch = Character.toLowerCase(ch);

        if (ch == 'a') {
            return "";
        } else {
            char prev = (char) (ch - 1);
            return String.valueOf(prev);
        }
    }
}
