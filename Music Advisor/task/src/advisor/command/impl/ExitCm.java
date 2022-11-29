package advisor.command.impl;

import advisor.command.Command;

public class ExitCm implements Command {

    private static final String EXIT_COMMAND = "exit";

    @Override
    public String name() {
        return EXIT_COMMAND;
    }

    @Override
    public void execute(String args) {
        System.out.println("---GOODBYE!---");
    }
}