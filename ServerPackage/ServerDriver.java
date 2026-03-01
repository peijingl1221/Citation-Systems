// The main program to run as a server

package ServerPackage;
import CommonPackage.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerDriver {
    private static HashMap<String, String> config;
    private static CitationList citationList;
    private static int port;
    private static Request request;
    private static RequestType userRequestType;
    private static FieldType userFieldType;
    private static Citation citation;


    public static void main(String[] args){
        config = Configuration.readConfigurationFile();
        String citationFileName = config.get("input file");
        citationList = new CitationList();
        citationList.readCitationFile(citationFileName);
        citationList.createMaps();
        port = Integer.parseInt(config.get("server port"));
        Server server = new Server(port);
        Citation citationToReturn = new Citation();
        do{
            request = server.receive();
            userRequestType = request.getRequestType();
            userFieldType = request.getFieldType();
            citation = request.getCitation();
            if (userRequestType.equals(RequestType.SEARCH)){
                switch (userFieldType){
                    case NUMBER:
                        citationToReturn = citationList.findByNumber(citation.getNumber());
                        break;
                    case NAME:
                        citationToReturn = citationList.findByName(citation.getLastName()).get(0);
                        break;
                    case TYPEOFOFFENSE:
                        citationToReturn = citationList.findByTypeOfOffense(citation.getTypeOfOffense()).get(0);
                        break;
                    case USERID:
                        citationToReturn = citationList.findByUserID(citation.getUserID()).get(0);
                        break;
                }
                server.send(citationToReturn);
            } else if(userRequestType.equals(RequestType.INSERT)){
                citationList.add(citation);
                server.send(new Citation());
            } else if(userRequestType.equals(RequestType.DELETE)){
                citationList.delete(citation);
                server.send(new Citation());
            }
        } while(userRequestType != RequestType.QUIT);
        server.close();

        String writeFileName = config.get("output file");
        ArrayList<Citation> listOfCitations = citationList.getListOfCitations();
        citationList.writeCitationFile(writeFileName);

    }
}
