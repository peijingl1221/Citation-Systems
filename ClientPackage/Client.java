// a proxy class that encapsulates the client-side communication

package ClientPackage;

import CommonPackage.Citation;
import CommonPackage.Request;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket clientSocket;
    private String jsonSend;
    private String jsonReceive;
    private String host;
    private int port;
    private Gson gson = new Gson();

    public Client(){
        host = "";
        port = 0;
    }

    // creates the socket to the host on that port, creates the input and output streams
    public Client(String host, int port){
        try {
            clientSocket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // method: send
    // converts the Request to a JSON record and sends it to the server
    public void send(Request request){
        jsonSend = gson.toJson(request);
        out.println(jsonSend);
    }

    // method: receive
    // receives a JSON record and converts it to a Citation object
    public Citation receive() {
        try {
            jsonReceive = in.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return gson.fromJson(jsonReceive, Citation.class);
    }

    // method: close
    // close the connection
    public void close(){
        try{
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

