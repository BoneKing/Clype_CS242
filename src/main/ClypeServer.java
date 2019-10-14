package main;

import data.ClypeData;

public class ClypeServer {
    private int port;
    private boolean closeConnection =false;
    private ClypeData dataToSendToServer;
    private ClypeData dataToReceiveFromServer;
    public static final int defaultPort = 7000;

    /**
     * constructor for ClypeServer
     * @param port
     */
    public ClypeServer(int port){
        this.port=port;
        dataToSendToServer=null;
        dataToReceiveFromServer=null;
    }

    /**
     * default Constructor for ClypeServer
     */
    public ClypeServer(){
        new ClypeServer(this.defaultPort);
    }

    /**
     * starts server (still a work in progress)
     */
    public void start(){

    }

    /**
     * will recieve data in the future
     */
    public void receiveData(){

    }

    /**
     * will send data in the future
     */
    public void sendData(){

    }

    /**
     *
     * @return port number
     */
    public int getPort() {
        return port;
    }

    /**
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getPort();
        result = 37 * result + (this.closeConnection ? 0:1);
        return  result;
    }

    /**
     * checks to see if two objects are equal
     * @param o
     * @return bool
     */
    @Override
    public boolean equals(Object o) {
        ClypeServer s = (ClypeServer) o;
        return this.getPort() == s.getPort() && this.closeConnection == s.closeConnection;
    }

    /**
     *
     * @return port number and closedConnection status
     */
    @Override
    public String toString() {
        return ("Port: "+this.getPort() + "\nClosed Connection: "+this.closeConnection);
    }
}
