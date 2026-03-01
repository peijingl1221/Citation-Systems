// The main program running as a client


package ClientPackage;
import CommonPackage.*;
import UserPackage.*;
import java.util.HashMap;
import java.util.Scanner;

public class ClientDriver {
    public static UserList userList;
    public static Client client;
    private static Menu menu;
    public static HashMap<String, String> config;
    public static User user;
    public static String host;
    public static int port;

    // method: newCitation
    // prompt the user to create a new citation
    public static Citation newCitation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the information for one new Citation");
        System.out.println("Enter the Citation number: ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the type of offense:");
        String typeOfOffence = sc.nextLine();
        System.out.println("Enter the description: ");
        String description = sc.nextLine();
        System.out.println("Enter the date: ");
        String date = sc.nextLine();
        System.out.println("Enter the person's first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter the person's last name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter the person's address: ");
        String address = sc.nextLine();
        System.out.println("Enter the person's phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Enter the the userID: ");
        int userID = Integer.parseInt(sc.nextLine());
        Person person = new Person(firstName, lastName, address, phoneNumber);
        Citation citation = new Citation(number, typeOfOffence, description, date, person, userID);
        return citation;
    }


    public static void main(String[] args) {
        menu = new Menu();
        config = Configuration.readConfigurationFile();
        String userFileName = config.get("user file");
        userList = new UserList();
        userList.readUserFile(userFileName);
        User userChoosed = userList.choose();
        host = config.get("host");
        port = Integer.parseInt(config.get("server port"));
        Client client = new Client(host, port);
        int userChoice;
        Request userRequest = null;
        RequestType userRequestType;
        FieldType userFieldType;
        Scanner sc = new Scanner(System.in);
        Citation citation = new Citation();
        do {
            userChoice = menu.displayMenu();
            switch (userChoice) {
                case 1:
                    System.out.println("Enter a citation number:");
                    int citationNumber = Integer.parseInt(sc.nextLine());
                    userRequestType = RequestType.SEARCH;
                    userFieldType = FieldType.NUMBER;
                    citation.setNumber(citationNumber);
                    userRequest = new Request(userRequestType, userFieldType, citation);
                    break;
                case 2:
                    System.out.println("Enter the last name:");
                    String lastName = sc.nextLine();
                    userRequestType = RequestType.SEARCH;
                    userFieldType = FieldType.NAME;
                    citation.setLastName(lastName);
                    userRequest = new Request(userRequestType, userFieldType, citation);
                    break;
                case 3:
                    System.out.println("Enter the type of offense:");
                    String typeOfOffense = sc.nextLine();
                    userRequestType = RequestType.SEARCH;
                    userFieldType = FieldType.TYPEOFOFFENSE;
                    citation.setTypeOfOffense(typeOfOffense);
                    userRequest = new Request(userRequestType, userFieldType, citation);
                    break;
                case 4:
                    int userID = userChoosed.getId();
                    userRequestType = RequestType.SEARCH;
                    userFieldType = FieldType.USERID;
                    citation.setUserID(userID);
                    userRequest = new Request(userRequestType, userFieldType, citation);
                    break;
                case 5:
                    Citation citation5 = ClientDriver.newCitation();
                    userRequestType = RequestType.INSERT;
                    userFieldType = null;
                    userRequest = new Request(userRequestType, userFieldType, citation5);
                    break;
                case 6:
                    System.out.println("Enter the citation number to delete:");
                    int citationDelete = Integer.parseInt(sc.nextLine());
                    userRequestType = RequestType.DELETE;
                    userFieldType = FieldType.NUMBER;
                    citation.setNumber(citationDelete);
                    userRequest = new Request(userRequestType, userFieldType, citation);
                    break;
            }
            client.send(userRequest);
            System.out.println(client.receive());
        } while (userChoice != 0);
        userRequestType = RequestType.QUIT;
        userFieldType = null;
        userRequest = new Request(userRequestType, userFieldType, citation);
        client.send(userRequest);
        client.close();
    }
}
