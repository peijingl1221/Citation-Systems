// A proxy class that encapsulates the server-side communication

package ServerPackage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import CommonPackage.*;
import com.google.gson.Gson;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private Socket clientConnection;
    private Scanner in;
    private PrintWriter out;
    private String jsonSend;
    private String jsonReceive;
    private Gson gson = new Gson();
    private Request request;

    public Server(int port) {
        this.port = port;
        try{
            serverSocket = new ServerSocket(port);
            clientConnection = serverSocket.accept();
            in = new Scanner(clientConnection.getInputStream());
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())), true);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // method: send
    // converts the Citation to a JSON record and sends it to the client
    public void send(Citation citation){
        jsonSend = gson.toJson(citation);
        out.println(jsonSend);
    }

    // method: receive
    // receives a JSON record and converts it to a Request object
    public Request receive(){
        jsonReceive = in.nextLine();
        request = gson.fromJson(jsonReceive, Request.class);
        return request;
    }

    // method: close
    // close the connection
    public void close(){
        try{
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
