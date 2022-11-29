package advisor.view;

import com.google.gson.JsonArray;

import java.util.Scanner;

import static advisor.Config.ITEM_PER_PAGE;

public class Output {

    public static void write(final JsonArray arr, final Format outputType) {

        final int pages = arr.size() / ITEM_PER_PAGE;

        int currentPage = 1;

        while (currentPage != 0) {
            for (int i = (currentPage - 1) * ITEM_PER_PAGE, j = 0; i < arr.size() && j < ITEM_PER_PAGE; i++, j++) {
                outputType.out(arr, i);
            }

            currentPage = Pagination(currentPage, pages);
        }
    }

    private static int Pagination(int currentPage, final int pages) {

        System.out.println("---PAGE " + currentPage + " OF " + pages + "---");
        System.out.println("Type next or prev to flip pages.");
        System.out.println("Press any key for back to menu.");
        switch (new Scanner(System.in).next()) {
            case "next":
                if (currentPage == pages) {
                    System.out.println("No more pages.");
                    break;
                }
                currentPage++;
                break;
            case "prev":
                if (currentPage == 1) {
                    System.out.println("No more pages.");
                    break;
                }
                currentPage--;
                break;
            default:
                return 0;
        }
        return currentPage;
    }

}
