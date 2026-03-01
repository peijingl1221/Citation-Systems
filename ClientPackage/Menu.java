// Displays the choices

package ClientPackage;

import java.util.Scanner;

public class Menu {
    private String[] choices = {
        "quit",
        "search by citation number",
        "search by last name",
        "search by type of offense",
        "search by user id",
        "create new citation",
        "delete citation by number"
    };

    public Menu(){
        choices = new String[]{
                "quit",
                "search by citation number",
                "search by last name",
                "search by type of offense",
                "search by user id",
                "create new citation",
                "delete citation by number"
        };
    };

    // method: displayMenu
    // displays the choices and returns user's choice
    public int displayMenu(){
        for(int i = 0; i < choices.length; i++){
            System.out.println(i + ": " + choices[i]);
        }
        System.out.println("Enter your choice:");
        int choice;
        Scanner sc = new Scanner(System.in);
        choice = Integer.parseInt(sc.nextLine());
        return choice;
    }
}
