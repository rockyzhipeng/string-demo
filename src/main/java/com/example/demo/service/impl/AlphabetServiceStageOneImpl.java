package com.example.demo.service.impl;

import com.example.demo.service.AlphabetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.constants.Constant.STAGE_ONE_PROCESS_MSG;
import static com.example.demo.constants.Constant.STAGE_ONE_PROCESS_RESULT_MSG;
import static com.example.demo.util.StringCommonUtil.findLongestConsecutiveStr;


@Slf4j
@Service
public class AlphabetServiceStageOneImpl implements AlphabetService {

    @Override
    public String process(final String input) {
        log.info(STAGE_ONE_PROCESS_MSG.formatted(input));
        if (input == null || input.isEmpty()) {
            return input;
        }

        String current = input;
        String consecutiveStr = findLongestConsecutiveStr(current);

        while (!consecutiveStr.isEmpty()) {
            // Replace a continuous sequence with the previous letter
            current = current.replace(consecutiveStr, "");
            log.info("Handling consecutive string remove, consecutiveStr is :[ {} ], "
                    + "new string is: [ {} ]", consecutiveStr, current);
            // Find new continuous sequence
            consecutiveStr = findLongestConsecutiveStr(current);
        }

        log.info(STAGE_ONE_PROCESS_RESULT_MSG.formatted(current));
        return current;
    }

}
