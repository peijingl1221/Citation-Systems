// Models a user, the parent of Administrator, CourtOfficial and Officer

package UserPackage;

public class User {
    private int id;              // the user's id number
    private String userName;     // the user's screen name

    public User(){
        id  = 0;
        userName = "";
    }

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString(){
        return "User id: " + id + "\nuser name: " + userName;
    }
}
