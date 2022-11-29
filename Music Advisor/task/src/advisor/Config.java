package advisor;

public final class Config {
    public static final String DEFAULT_ACCOUNT_URL = "https://accounts.spotify.com";

    public static final String DEFAULT_API_URL = "https://api.spotify.com";

    public static final int DEFAULT_ITEMS_PER_PAGE = 5;

    public  static final String JSON_ITEMS_FIELD_NAME = "items";
    public static final String CLIENT_ID = "b180b9e7265640b49c33ea7cccca13ed";
    public static final String CLIENT_SECRET = "aa786ac62b444b308c4396f68c090c96";
    public static final String REDIRECT_URI = "http://localhost:8080";

    public static String API_SERVER, AUTH_SERVER, ACCESS_TOKEN;
    public static Integer ITEM_PER_PAGE;

    public static void config(final String... args) {

        AUTH_SERVER = (args != null && args.length > 1 && "-access".equals(args[0])) ?
                args[1] :
                DEFAULT_ACCOUNT_URL;
        API_SERVER = (args != null && args.length > 3 && "-resource".equals(args[2])) ?
                args[3] :
                DEFAULT_API_URL;
        ITEM_PER_PAGE = (args != null && args.length > 5 && "-page".equals(args[4])) ?
                Integer.parseInt(args[5]) :
                DEFAULT_ITEMS_PER_PAGE;

    }
}
