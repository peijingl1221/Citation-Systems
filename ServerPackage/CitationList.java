// Encapsulates the list of citations


package ServerPackage;
import CommonPackage.*;

import java.io.*;
import java.util.*;


public class CitationList {

    private ArrayList<Citation> listOfCitations;      // an arraylist which contains citations
    private String title;                             // title of the official who is in charge of the citations
    private String authority;                         // authority of the official who is in charge of the citations
    private HashMap<Citation, HashMap<String, ArrayList<Citation>>> maps;

    public CitationList(){
        listOfCitations = new ArrayList<>();
        maps = new HashMap<>();
        title = "Chief";
        authority = "Barrett";
    }

    public CitationList(String title, String authority){
        listOfCitations = new ArrayList<>();
        this.title = title;
        this.authority = authority;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthority(String authority){
        this.authority = authority;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthority(){
        return authority;
    }
    public ArrayList<Citation> getListOfCitations(){return listOfCitations;}
    public HashMap<Integer, Citation> numberMap = new HashMap<>();
    public HashMap<String, ArrayList<Citation>> lastNameMap = new HashMap<>();
    public HashMap<String, ArrayList<Citation>> typeOfOffenseMap = new HashMap<>();
    public HashMap<Integer, ArrayList<Citation>> userIDMap = new HashMap<>();

    // method: findByNumber
    // find the citation by number
    public Citation findByNumber(int number){
        return numberMap.get(number);
    }

    // method: findByName
    // find the citations according to the person's lastname
    public ArrayList<Citation> findByName(String lastName){
        return lastNameMap.get(lastName);
    }

    // method: findByTypeOfOffense
    // find the citations of the type of offense
    public ArrayList<Citation> findByTypeOfOffense(String typeOfOffense){
        return typeOfOffenseMap.get(typeOfOffense);
    }

    // method: findByUserID
    // find the citations based on UserID
    public ArrayList<Citation> findByUserID(int userId){
        return userIDMap.get(userId);
    }

    // method: add
    // add a new citation to citation list and maps
    public void add(Citation citation){
        listOfCitations.add(citation);
        numberMap.put(citation.getNumber(), citation);
        lastNameMap.get(citation.getLastName()).add(citation);
        typeOfOffenseMap.get(citation.getTypeOfOffense()).add(citation);
        userIDMap.get(citation.getUserID()).add(citation);
    }

    // method: delete
    // delete a citation from citation list and maps
    public void delete(Citation citation){
        listOfCitations.remove(citation);
        numberMap.remove(citation.getNumber());
        for(ArrayList<Citation> l: lastNameMap.values()){
            Iterator<Citation> it = l.iterator();
            while(it.hasNext()){
                Citation c = it.next();
                if(c.getNumber() == citation.getNumber()){
                    it.remove();
                }
            }
        }
        for(ArrayList<Citation> l: typeOfOffenseMap.values()){
            Iterator<Citation> it = l.iterator();
            while(it.hasNext()){
                Citation c = it.next();
                if(c.getNumber() == citation.getNumber()){
                    it.remove();
                }
            }
        }
        for(ArrayList<Citation> l: userIDMap.values()){
            Iterator<Citation> it = l.iterator();
            while(it.hasNext()){
                Citation c = it.next();
                if(c.getNumber() == citation.getNumber()){
                    it.remove();
                }
            }
        }

    }

    // method: createMaps
    // for each citation, create maps for number, lastname, type of offense and userid, easy for searching
    public void createMaps(){
        for(Citation c: listOfCitations){
            int number = c.getNumber();
            String lastName = c.getLastName();
            String typeOfOffense = c.getTypeOfOffense();
            int userID = c.getUserID();
            numberMap.put(number, c);
            if (lastNameMap.containsKey(lastName)){
                lastNameMap.get(lastName).add(c);
            } else{
                lastNameMap.put(lastName, new ArrayList<>(Arrays.asList(c)));
            }
            if (typeOfOffenseMap.containsKey(typeOfOffense)){
                typeOfOffenseMap.get(typeOfOffense).add(c);
            }  else{
                typeOfOffenseMap.put(typeOfOffense, new ArrayList<>(Arrays.asList(c)));
            }
            if(userIDMap.containsKey(userID)){
                userIDMap.get(userID).add(c);
            }  else{
                userIDMap.put(userID, new ArrayList<>(Arrays.asList(c)));
            }
        }
    }


    // method: readCitationFile
    // read the CSV file and add each citation to the listOfCitations
    public void readCitationFile(String fileName){
        this.listOfCitations = new ArrayList<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner fileScanner = new Scanner(in);
        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            String[] lineArray = line.split(",");
            Citation citation = new Citation();
            citation.setNumber(Integer.parseInt(lineArray[0]));
            citation.setTypeOfOffense(lineArray[1]);
            citation.setDescription(lineArray[2]);
            citation.setDate(lineArray[3]);
            citation.setPerson(new Person(lineArray[4], lineArray[5], lineArray[6], lineArray[7]));
            citation.setUserID(Integer.parseInt(lineArray[8]));
            this.listOfCitations.add(citation);
        }
    }



    // method: writeCitationFile
    // write citations into a new CSV file
    public void writeCitationFile(String filename){
        try{
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Citation citation : listOfCitations){
                bufferedWriter.write(citation.toCSV());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
