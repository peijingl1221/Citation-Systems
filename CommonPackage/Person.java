// Models the person receiving a citation
package CommonPackage;

public class Person {
    private String firstName;           // the person's first name
    private String lastName;            // the person's last name
    private String address;             // the person's address
    private String phoneNumber;         // the person's phone number

    public Person(){
        firstName = "";
        lastName = "";
        address = "";
        phoneNumber = "";
    }
    public Person(String firstName, String lastName, String address, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getAddress(){
        return address;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String toString(){
        return "First Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber;
    }

    public String toCSV(){
        return firstName + "," + lastName + "," + address + "," + phoneNumber;
    }
}
