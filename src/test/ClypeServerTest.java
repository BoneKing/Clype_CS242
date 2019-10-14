package test;


import data.ClypeData;
import main.ClypeServer;

public class ClypeServerTest{
    public static void main(String args[]){
        ClypeServer CS = new ClypeServer();
        ClypeServer CS2 = new ClypeServer(-6969);
        /*
            Ports can also be negative here for the same reasons discussed on ClypeClientTest.java
         */
        System.out.println("server info for CS: ");
        System.out.println("port = "+CS.getPort());
        System.out.println("hashcode ="+CS.hashCode());
        System.out.println(CS.toString());

        System.out.println("server info for CS2: ");
        System.out.println("port = "+CS2.getPort());
        System.out.println("hashcode ="+CS2.hashCode());
        System.out.println(CS2.toString());

    }
}