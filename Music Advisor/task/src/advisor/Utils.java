package advisor;

import advisor.command.Command;
import advisor.command.impl.UnknownCm;

import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String scannedWords() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Command enteredCommand(final String commandName, final Iterable<Command> availableCommands) {

        return StreamSupport.stream(availableCommands.spliterator(), false)
                .filter(command -> commandName.split(" ", 2)[0].equals(command.name()))
                .findAny()
                .orElse(new UnknownCm());
    }

}

