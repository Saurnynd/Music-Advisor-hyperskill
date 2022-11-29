package advisor.command.impl;

import advisor.command.Command;

public class UnknownCm implements Command {

    private static final String UNKNOWN_COMMAND = "Unknown command";

    @Override
    public String name() {
        return UNKNOWN_COMMAND;
    }

    @Override
    public void execute(final String str) {
        System.out.println("Unknown command entered");
    }
}