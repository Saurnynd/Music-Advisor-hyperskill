package advisor.command;

public interface Command {
    String name();

    void execute(String str);
}