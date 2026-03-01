// Models Administrator, a child of User

package UserPackage;

public class Administrator extends User{
    private String department;     // where the administrator works

    public Administrator(){
        super();
        department = "";
    }

    public Administrator(int id, String userName, String department){
        super(id, userName);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String toString(){
        return super.toString() + "\ndepartment: " + department;
    }
}
