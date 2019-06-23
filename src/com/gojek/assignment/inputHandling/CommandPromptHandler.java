package com.gojek.assignment.inputHandling;

import com.gojek.assignment.abstractProcessor.CommandsProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandPromptHandler extends CommandsProcessor {

    public void process() throws Exception {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));


        while(true) {
            String inputString = bufferRead.readLine();
            validateAndProcess(inputString);
        }
    }



}