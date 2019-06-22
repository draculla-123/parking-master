package com.gojek.assignment;

import com.gojek.assignment.inputHandling.CommandPromptHandler;
import com.gojek.assignment.inputHandling.FileHandler;
import com.gojek.assignment.abstractProcessor.CommandsProcessor;

public class Main {

    public static void main(String[] args) throws Exception {

        CommandsProcessor commandsProcessor;

        if(args.length >= 1) {
            commandsProcessor = new FileHandler(args[0]);
        } else {
            commandsProcessor = new CommandPromptHandler();
        }
        commandsProcessor.process();
    }

}