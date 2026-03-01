// Uses Factory Pattern to create one of the child classes of User

package UserPackage;

public class UserFactory {
    // method: createUser
    // return a child class of User based on the userType
    public static User createUser(int id, String userType, String name, String other){
        User user = null;
        if(userType.equals("Administrator")){
            user = new Administrator(id, name, other);
        }else if(userType.equals("Officer")){
            user = new Officer(id, name, other);
        }else if(userType.equals("CourtOfficial")){
            user = new CourtOfficial(id, name, other);
        }
        return user;
    }
}
