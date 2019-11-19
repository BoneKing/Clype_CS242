package main;

import data.ClypeData;
import java.io.*;
import  java.net.*;
import java.util.*;

public class ClypeServer {
    private int port;
    private boolean closeConnection = false;
    //private ClypeData dataToSendToClient;
    //private ClypeData dataToReceiveFromClient;
    public static final int defaultPort = 7000;
    //private ObjectInputStream inFromClient = null;
    //private ObjectOutputStream outToClient = null;
    private ServerSocket sskt=null;
    private Socket cskt = null;
    private ArrayList<ServerSideClientIO> serverSideClientIOList = new ArrayList<ServerSideClientIO> ();

    /**
     * constructor for ClypeServer
     *
     * @param port
     */
    public ClypeServer(int port) {
        try {
            this.port = port;
            ServerSideClientIO serverSideClientIOList[]={};
        } catch (IllegalArgumentException aie) {
            System.err.println("Illegal port");
        }
    }

    /**
     * default Constructor for ClypeServer
     */
    public ClypeServer() {
        this(defaultPort);
    }

    /**
     * starts server <br>
     * opens server socket and accepts client socket <br>
     * it handles recieving and sending data <br>
     * closes streams and sockets when completed <br>
     */
    public void start() {
       try {
           sskt = new ServerSocket(port);
           System.out.println("socket for server opened");
           while(closeConnection == false) {
               cskt = sskt.accept();
               System.out.println("client accepted");
               ServerSideClientIO nSSCIO = new ServerSideClientIO(this, cskt);
               serverSideClientIOList.add(nSSCIO);
               Thread t = new Thread(new ServerSideClientIO(this, cskt));
               t.start();
           }
           //outToClient = new ObjectOutputStream(cskt.getOutputStream());
           //inFromClient = new ObjectInputStream(cskt.getInputStream());

           cskt.close();
           sskt.close();
       } catch (IOException ioe) {
           System.err.println("IO error: " + ioe.getMessage());
           closeConnection =true;
       }
    }
    /**
     * @return port number
     */
    public int getPort() {
        return port;
    }

    /**
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getPort();
        result = 37 * result + (this.closeConnection ? 0 : 1);
        return result;
    }

    /**
     * checks to see if two objects are equal
     *
     * @param o
     * @return bool
     */
    @Override
    public boolean equals(Object o) {
        ClypeServer s = (ClypeServer) o;
        return this.getPort() == s.getPort() && this.closeConnection == s.closeConnection;
    }

    /**
     * @return port number and closedConnection status
     */
    @Override
    public String toString() {
        return ("Port: " + this.getPort() + "\nClosed Connection: " + this.closeConnection);
    }

    /**
     *
     * @param dataToBroadcastToClients
     * sets data to send and sends data to all clients.
     */
    public synchronized void broadcast(ClypeData dataToBroadcastToClients){
        for(int i =0; i< serverSideClientIOList.size();i++){
            ServerSideClientIO tmp = serverSideClientIOList.get(i);
            tmp.setDataToSendToClient(dataToBroadcastToClients);
            tmp.sendData();
        }
    }
    public synchronized void remove(ServerSideClientIO serverSideClientToRemove){
        serverSideClientIOList.remove(serverSideClientToRemove);
    }
    public void LISTUSERS(){
        for(int i=0;i<serverSideClientIOList.size();i++){
            ServerSideClientIO tmp = serverSideClientIOList.get(i);
            //tmp.setDataToSendToClient();
            tmp.sendData();
        }
    }
    public static void main(String args[]) {
        if(args.length==0){
            ClypeServer CS = new ClypeServer();
            CS.start();
        }
        else{
            int port = Integer.parseInt(args[0]);
            ClypeServer CS = new ClypeServer(port);
            CS.start();
        }
    }
}