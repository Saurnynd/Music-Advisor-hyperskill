package advisor.menu.impl;

import advisor.Utils;
import advisor.command.impl.AuthCm;
import advisor.command.impl.CategoriesCm;
import advisor.command.Command;
import advisor.command.impl.EntersMenuAfterExecution;
import advisor.command.impl.ExitCm;
import advisor.command.impl.FeaturedCm;
import advisor.command.impl.NewCm;
import advisor.command.impl.PlaylistsCm;
import advisor.command.impl.UnknownCm;
import advisor.menu.CommandsMenu;

import java.util.List;

import static advisor.Utils.scannedWords;

public class EntryMenu implements CommandsMenu {

    private static EntryMenu menu;

    private EntryMenu() {
    }

    public static EntryMenu getMenu() {

        if (menu == null) {
            menu = new EntryMenu();
        }

        return menu;
    }

    private record CommandThatNeedsAccess(Command command) implements Command {

        @Override
        public String name() {
            return command.name();
        }

        @Override
        public void execute(String args) {
            System.out.println("Please, provide access for application.");
        }
    }

    final List<Command> availableCommands = List.of(
            new ExitCm(),
            new EntersMenuAfterExecution(new CommandThatNeedsAccess(new NewCm()), this),
            new EntersMenuAfterExecution(new CommandThatNeedsAccess(new CategoriesCm()), this),
            new EntersMenuAfterExecution(new CommandThatNeedsAccess(new FeaturedCm()), this),
            new EntersMenuAfterExecution(new CommandThatNeedsAccess(new PlaylistsCm()), this),
            new EntersMenuAfterExecution(new AuthCm(), new AfterAuthMenu()),
            new EntersMenuAfterExecution(new UnknownCm(), this));

    public void enter() {
        System.out.println("---AUTH MENU---");
        System.out.println("Type auth for log in OR exit for exit.");
        final String inputCommand = scannedWords();
        Command command = Utils.enteredCommand(inputCommand, availableCommands);
        command.execute(inputCommand);
    }

}