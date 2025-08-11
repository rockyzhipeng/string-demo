package com.example.demo.service.impl;

import com.example.demo.service.AlphabetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.constants.Constant.STAGE_TWO_PROCESS_MSG;
import static com.example.demo.constants.Constant.STAGE_TWO_PROCESS_RESULT_MSG;
import static com.example.demo.util.StringCommonUtil.findLongestConsecutiveStr;
import static com.example.demo.util.StringCommonUtil.getPreLetter;

@Slf4j
@Service
public class AlphabetServiceStageTwoImpl implements AlphabetService {

    @Override
    public String process(final String input) {
        log.info(STAGE_TWO_PROCESS_MSG.formatted(input));
        if (input == null || input.isEmpty()) {
            return input;
        }

        String current = input;
        String consecutiveStr = findLongestConsecutiveStr(current);

        while (!consecutiveStr.isEmpty()) {
            // Get the first character of the continuous sequence
            String letter = String.valueOf(consecutiveStr.charAt(0));

            // Replace a continuous sequence with the previous letter
            String preLetter = getPreLetter(letter);
            current = current.replace(consecutiveStr, preLetter);
            log.info("Handling consecutive string remove and replace, consecutiveStr is :[ {} ],"
                    + "replaced by: [ {} ], new string is: [ {} ]", consecutiveStr, preLetter, current);

            // Find new continuous sequence
            consecutiveStr = findLongestConsecutiveStr(current);
        }

        log.info(STAGE_TWO_PROCESS_RESULT_MSG.formatted(current));
        return current;
    }
}
