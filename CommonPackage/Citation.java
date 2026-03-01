// Models one ticket

package CommonPackage;

public class Citation implements Comparable<Citation> {
    private int number;                     // ticket number
    private int userID;                     // id of  the person in charge of this citation
    private String title;                   // the title of the official who are in charge of all citations
    private String author;                  // the name of the official who are in charge of all citations
    private String typeOfOffense;           // the type of offense of the ticket
    private String description;             // the description of the offense
    private String date;                    // the date when the offense happened
    private Person person;                  // an object of the class CitationPackage.Person, aka information of the person who offensed

    public Citation(){
        number = 0;
        typeOfOffense = "";
        description = "";
        date = "";
        person = new Person();
        userID = 0;
    }

    public Citation(int number, String typeOfOffense, String description, String date, Person person, int userID){
        this.number = number;
        this.typeOfOffense = typeOfOffense;
        this.description = description;
        this.date = date;
        this.person = person;
        this.userID = userID;
    }

    public void setNumber(int number){
        this.number = number;
    }
    public void setUserID(int userID){this.userID = userID;}
    public void setTypeOfOffense(String typeOfOffense){
        this.typeOfOffense = typeOfOffense;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setPerson(Person person){
        this.person = person;
    }
    public void setLastName(String lastName){this.person.setLastName(lastName);}
    public void setFirstName(String firstName){}
    public int getNumber(){
        return number;
    }
    public int getUserID(){return userID;}
    public String getTypeOfOffense(){
        return typeOfOffense;
    }
    public String getDescription(){
        return description;
    }
    public String getDate(){
        return date;
    }
    public Person getPerson(){
        return person;
    }
    public String getLastName(){return person.getLastName();}
    public String getFirstName(){return person.getFirstName();}

    // method: compareTo
    // sort by citation number
    public int compareTo(Citation citation){
        return this.getNumber() - citation.getNumber();
    }

    public String toString(){
        return "Citation #" + number +
                "\nType of Offense: " + typeOfOffense +
                "\nDescription: " + description +
                "\nDate: " + date +
                "\nPerson: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() + " " + person.getPhoneNumber() +
                "\nUserID: " + userID;
    }

    // method: toCSV
    // return a CSV String used for writing to a file
    public String toCSV(){
        return number + "," + typeOfOffense + "," + description + "," + date + "," + person.toCSV() + "," + userID;
    }

}
