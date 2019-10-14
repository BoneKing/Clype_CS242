package main;

import data.ClypeData;
import data.FileClypeData;
import data.MessageClypeData;

import java.util.Scanner;

public class ClypeClient {
    final static int defaultport = 7000;
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection=false;
    private ClypeData dataToSendToServer = null;
    private ClypeData dataToReceiveFromServer =null;
    private Scanner inFromStd;

    /**
     * constructor for ClypeClient
     * @param userName
     * @param hostName
     * @param port
     */
    public ClypeClient(String userName, String hostName, int port){
        dataToSendToServer = null;
        dataToReceiveFromServer = null;
        this.userName=userName;
        this.hostName=hostName;
        this.port=port;
        if(userName == null || hostName == null || port < 1024)
            throw new IllegalArgumentException("\nInvalid Input");
    }

    /**
     * alternate constructor for ClypeClient
     * @param userName
     * @param hostName
     * @throws IllegalArgumentException
     */
    public ClypeClient(String userName, String hostName) throws IllegalArgumentException{
        this(userName, hostName, defaultport);
        if(userName == null || hostName == null)
            throw new IllegalArgumentException("\nInvalid Input");
    }

    /**
     * alternate constructor for ClypeClient
     * @param userName
     * @throws IllegalArgumentException
     */
    public ClypeClient(String userName) throws  IllegalArgumentException{
        this(userName, "localhost", defaultport);
        if(userName == null)
            throw new IllegalArgumentException("\nInvalid Input");
    }

    /**
     * default constructor for ClypeClient
     */
    public  ClypeClient(){
        this("anonymous");
    }

    /**
     * starts the client then read and prints data <br>
     * also instantiates a scanner called inFromStd
     */
    public void start(){
        this.inFromStd = new Scanner(System.in);
        dataToReceiveFromServer=dataToSendToServer;
        readClientData();
        printData();
    }

    /**
     * reads data and changes action based on what is read from the inFromStd Scanner
     */
    public void readClientData(){
        if(inFromStd.toString() == "DONE"){
            closeConnection=true;
        }
        else if(inFromStd.toString() == "SENDFILE"){
            String filename = inFromStd.toString();
            dataToSendToServer = new FileClypeData(userName,filename,2);
        }
        else if(inFromStd.toString() == "LISTUSERS"){
            /*this does nothing for now but
            will eventually return a list of users
            or an error if that's not possible
             */
        }
        else{
            String message = inFromStd.toString();
            dataToSendToServer = new MessageClypeData(userName, message,3);
        }

    }
    public void sendData(){

    }
    public void receiveData(){

    }

    /**
     * prints data that is being sent or received
     */
    public void printData(){
        this.dataToReceiveFromServer = this.dataToSendToServer;
        System.out.println(this.dataToReceiveFromServer.toString());
    }

    /**
     *
     * @return userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     *
     * @return hostName
     */
    public String getHostName() {
        return this.hostName;
    }

    /**
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result =17;
        result = 37 * result + this.getUserName().hashCode();
        result = 37 * result + this.getHostName().hashCode();
        result = 37 * result + this.getPort();
        result = 37 *result + (this.closeConnection ? 0:1);
        return result;

    }

    /**
     * checks to see if two objects are equal
     * @param o
     * @return bool
     */
    @Override
    public boolean equals(Object o) {
        ClypeClient c = (ClypeClient) o;
        return( this.userName.equals(c.getUserName())&&
                this.hostName.equals(c.getHostName())&&
                this.port == getPort() &&
                this.closeConnection == c.closeConnection);
    }

    /**
     *
     * @return every piece of data the object has which can be userName,hostName, port, closed connection <br>
     *  and may include dataToSendToServer and dataToReceiveFromServer
     */
    @Override
    public String toString() {
        if(this.dataToReceiveFromServer != null && this.dataToSendToServer != null){
            return ("Description: this is client"+
                    "\nUser name: "+this.getUserName() +
                    "\nHost Name: "+this.getHostName() +
                    "\nPort: "+this.getPort()+
                    "\nClose Connection: "+this.closeConnection+
                    "\nData to Send to Server: "+this.dataToSendToServer+
                    "\nData to Recieve From Server: "+this.dataToReceiveFromServer);
        }
        else{
            return ("Description: this is client"+
                    "\nUser name: "+this.getUserName() +
                    "\nHost Name: "+this.getHostName() +
                    "\nPort: "+this.getPort()+
                    "\nClose Connection: "+this.closeConnection);
        }
    }
}
