// Models Officer, a child of User

package UserPackage;

public class Officer extends User{
    String rank;        // one of Regular, Sargent, Captain, Inspector

    public Officer(){
        super();
        rank = "";
    }

    public Officer(int id, String userName, String rank) {
        super(id, userName);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    public String toString(){
        return super.toString() + "\nrank: " + rank;
    }
}
