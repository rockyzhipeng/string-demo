package com.example.demo.service;

import com.example.demo.service.impl.AlphabetServiceStageOneImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.example.demo.constants.Constant.STAGE_ONE_PROCESS_MSG;
import static com.example.demo.constants.Constant.STAGE_ONE_PROCESS_RESULT_MSG;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
public class AlphabetServiceStageOneImplTest {

    @InjectMocks
    private AlphabetServiceStageOneImpl alphabetService;

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
        String input = "aabbbccddeeff";
        String expected = "aaccddeeff";

        String result = alphabetService.process(input);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(STAGE_ONE_PROCESS_MSG.formatted(input)));
        assertTrue(output.getOut().contains(STAGE_ONE_PROCESS_RESULT_MSG.formatted(expected)));
    }

}
