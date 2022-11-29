package advisor.menu.impl;

import advisor.Utils;
import advisor.command.*;
import advisor.command.impl.CategoriesCm;
import advisor.command.impl.EntersMenuAfterExecution;
import advisor.command.impl.ExitCm;
import advisor.command.impl.FeaturedCm;
import advisor.command.impl.NewCm;
import advisor.command.impl.PlaylistsCm;
import advisor.command.impl.UnknownCm;
import advisor.menu.CommandsMenu;

import java.util.List;

import static advisor.Utils.scannedWords;

public class AfterAuthMenu implements CommandsMenu {
    private final Command newCommand = new NewCm();
    private final Command categoriesCommand = new CategoriesCm();
    private final Command featuredCommand = new FeaturedCm();
    private final Command playlistsCommand = new PlaylistsCm();

    final List<Command> availableCommands = List.of(
            new ExitCm(),
            new EntersMenuAfterExecution(newCommand, this),
            new EntersMenuAfterExecution(categoriesCommand, this),
            new EntersMenuAfterExecution(featuredCommand, this),
            new EntersMenuAfterExecution(playlistsCommand, this),
            new EntersMenuAfterExecution(new UnknownCm(), this));

    public void enter() {
        System.out.println("---MENU---");
        final String inputCommand = scannedWords();
        Command command = Utils.enteredCommand(inputCommand, availableCommands);
        command.execute(inputCommand);
    }
}