package main;

public class ClientSideServerListener {
    private ClypeClient client;

    public ClientSideServerListener(client){

    }
    public run(){
        while(client.closeConnection == false){
            client.receiveData();
            client.printData();
        }
    }
}
