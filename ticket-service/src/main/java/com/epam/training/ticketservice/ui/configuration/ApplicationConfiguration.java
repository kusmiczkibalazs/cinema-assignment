package com.epam.training.ticketservice.ui.configuration;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;
import com.epam.training.ticketservice.ui.interpreter.CommandLineInterpreter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CommandLineInterpreter commandLineInterpreter(Set<AbstractCommand> abstractCommands) {
        return new CommandLineInterpreter(System.in, System.out, abstractCommands);
    }
}
