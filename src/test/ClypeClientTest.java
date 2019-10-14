package test;

import data.ClypeData;
import main.ClypeClient;

public class ClypeClientTest{
    public static void main(String args[]) {
        ClypeClient CC = new ClypeClient("UseyBoi", "hostyBoi", 1025);
        /*
            As you can see from the line above, ClypeClient currently doesn't check for a negativ port number
            This feature will have to added in later with input sanitizing as port numbers can't be negative.
         */
        ClypeClient CC2 = new ClypeClient("notNull", "hostyBoy2");
        /*
            putting in Null for the username will result in Username being set to null.
            This could be fixed by defining a default user name or denying connection if no username is given
         */
        ClypeClient CC3 = new ClypeClient("UseyBoi3");

        System.out.println("CC Hostname = "+CC.getHostName());
        System.out.println("CC Username = "+CC.getUserName());
        System.out.println("CC port = "+CC.getPort());
        System.out.println("hashcode = "+CC.hashCode());
        System.out.println(CC.toString());
        System.out.println("hashcode for CC2 = "+CC2.hashCode());
        System.out.println("CC2 Hostname = "+CC2.getHostName());
        System.out.println("CC2 Username = "+CC2.getUserName());
        System.out.println("CC2 port = "+CC2.getPort());
        System.out.println(CC2.toString());
        System.out.println("hashcode for CC3 = "+CC3.hashCode());
        System.out.println("CC3 Hostname = "+CC3.getHostName());
        System.out.println("CC3 Username = "+CC3.getUserName());
        System.out.println("CC3 port = "+CC3.getPort());
        System.out.println(CC3.toString());
        CC3.start();
        CC3.printData();
        System.out.println("\ntesting toString() on CC3: ");
        System.out.println(CC3.toString());
        System.out.println("ending test of toString()\n");
        System.out.println("does CC3 = CC2? " +CC3.equals(CC2));
        System.out.println("CC3 Hashcode "+CC3.hashCode());
    }
}