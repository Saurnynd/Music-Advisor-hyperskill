package advisor;

import advisor.menu.impl.EntryMenu;

import static advisor.Config.config;


public class Main {

    public static void main(String[] args) {

        config(args);

        EntryMenu.getMenu().enter();
    }
}