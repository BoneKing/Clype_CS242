package main;
import data.ClypeData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable{
    private boolean closeConnection = false;
    ClypeData dataToReceiveFromClient = null;
    ClypeData dataToSendToClient = null;
    ObjectInputStream inFromClient = null;
    ObjectOutputStream outToClient = null;
    ClypeServer server = null;
    Socket clientSocket = null;

    ServerSideClientIO(ClypeServer server, Socket clientSocket){
        this.server=server;
        this.clientSocket=clientSocket;
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException ioe) {
            System.err.println("IO error: " + ioe.getMessage());
            server.remove(this);
        }

    }
    @Override
    public void run() {
        while(closeConnection == false){
            recieveData();
            this.server.broadcast(dataToReceiveFromClient);
        }
    }
    /**
    * Opens a server and accepts a client socket <br>
    * it recieves and echoes data from client then closes <br>
    */
    public void recieveData(){
        try {
            dataToReceiveFromClient = (ClypeData)inFromClient.readObject();
            //for debugging
            System.out.println(dataToReceiveFromClient);
        } catch (IOException ioe) {
            System.err.println("IO error: " + ioe.getMessage());
            server.remove(this);
        } catch (IllegalArgumentException | ClassNotFoundException iae) {
            System.err.println("Illegal Arguement: " + iae.getMessage());
            server.remove(this);
        }
    }

    /**
     * Sends data <br>
     * Opens server socket and accepts client socket <br>
     * sends data to client and closes all sockets and steams when completed <br>
     */
    public void sendData(){
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println("IO error: " + ioe.getMessage());
            closeConnection =true;
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal Arguement: " + iae.getMessage());
            closeConnection =true;
        }
    }
    public void setDataToSendToClient(ClypeData dataToSendToClient){
        this.dataToSendToClient=dataToSendToClient;
    }
}
