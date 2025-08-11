package com.example.demo;

import com.example.demo.service.AlphabetService;
import com.example.demo.service.impl.AlphabetServiceStageOneImpl;
import com.example.demo.service.impl.AlphabetServiceStageTwoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        AlphabetService alphabetServiceStageOne = new AlphabetServiceStageOneImpl();
        AlphabetService alphabetServiceStageTwo = new AlphabetServiceStageTwoImpl();

        alphabetServiceStageOne.process("aabcccbbad");

        System.out.println();

        alphabetServiceStageTwo.process("abccccbad");
    }

}
