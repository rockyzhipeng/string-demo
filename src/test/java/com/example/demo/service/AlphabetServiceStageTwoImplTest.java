package com.example.demo.service;


import com.example.demo.service.impl.AlphabetServiceStageTwoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.example.demo.constants.Constant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
public class AlphabetServiceStageTwoImplTest {

    @InjectMocks
    private AlphabetServiceStageTwoImpl alphabetService;

    @Test
    void process_WithNullInput_ShouldReturnNull() {
        String result = alphabetService.process(null);

        assertNull(result);
    }

    @Test
    void process_WithEmptyInput_ShouldReturnEmpty() {
        String result = alphabetService.process("");

        assertEquals("", result);
    }

    @Test
    void process_WithMultipleConsecutiveSets_Success(final CapturedOutput output) {
        String input = "abcccbad";
        String expected = "d";

        String result = alphabetService.process(input);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(STAGE_TWO_PROCESS_MSG.formatted(input)));
        assertTrue(output.getOut().contains(STAGE_TWO_PROCESS_RESULT_MSG.formatted(expected)));
    }
}
