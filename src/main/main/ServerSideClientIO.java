package main;
import data.ClypeData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable{
    private boolean closeConnection = false;
    ClypeData dataToReciveFromClient = null;
    ClypeData dataToSendToClient = null;
    ObjectInputStream inFromClient = null;
    ObjectOutputStream outToClient = null;
    ClypeServer server = null;
    Socket clientSocket = null;

    ServerSideClientIO(ClypeServer server, Socket clientSocket){
        this.server=server;
        this.clientSocket=clientSocket;
    }
    @Override
    public void run() {
        while(closeConnection == false){
            recieveData();
            this.server.broadcast();
        }
    }
    public void recieveData(){
        try {
            inFromClient = new ObjectInputStream(cskt.getInputStream());
            dataToReceiveFromClient = (ClypeData)inFromClient.readObject();
            //for debugging
            System.out.println(dataToReceiveFromClient);
        } catch (IOException ioe) {
            System.err.println("IO error: " + ioe.getMessage());
            remove();
        } catch (IllegalArgumentException | ClassNotFoundException iae) {
            System.err.println("Illegal Arguement: " + iae.getMessage());
            remove();
        }
    }
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
    public void setDataToSendToClient(dataToSendToClient){
        this.dataToSendToClient=dataToSendToClient;
    }
}
