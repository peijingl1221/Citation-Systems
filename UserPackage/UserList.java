// Models the list of users

package UserPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private ArrayList<User> listOfUsers = new ArrayList<>();     // user data

    public UserList() {
        listOfUsers = new ArrayList<>();
    }

    public UserList(ArrayList<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(ArrayList<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }


    // method: readUserFile
    // read the user data into listOfUsers
    public void readUserFile(String filename) {
        this.listOfUsers = new ArrayList<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
        Scanner fileScanner = new Scanner(in);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] lineArray = line.split(",");
            User user = UserFactory.createUser(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2], lineArray[3]);
            user.setId(Integer.parseInt(lineArray[0]));
            user.setUserName(lineArray[2]);
            listOfUsers.add(user);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : listOfUsers) {
            sb.append(user.toString() + "\n");
        }
        return sb.toString();
    }

    // method: choose
    // return a valid user from the user list
    public User choose(){
        System.out.println("Pick one from the following users, enter id:");
        for (User user : listOfUsers) {
            System.out.println(user.getId() + ": " + user.getUserName());
        }
        Scanner sc = new Scanner(System.in);
        int id = Integer.parseInt(sc.nextLine());
        for (User user : listOfUsers) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}


