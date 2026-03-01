// Models CourtOfficial, a child of User

package UserPackage;

public class CourtOfficial extends User{
    String title;     // what this person’s role in the court system is

    public CourtOfficial(){
        super();
        title = "";
    }

    public CourtOfficial(int id, String userName, String title){
        super(id, userName);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String toString(){
        return super.toString() + "\ntitle: " + title;
    }
}
