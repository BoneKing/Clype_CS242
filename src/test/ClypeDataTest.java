package test;

import data.ClypeData;
import data.FileClypeData;
import data.MessageClypeData;

public class ClypeDataTest {
    public static void main(String args[]){
        ClypeData M =new MessageClypeData("clypeUser","test", 0);
        ClypeData F = new FileClypeData("fuser","ClypeData.java",0);
        ClypeData E = new MessageClypeData("Euser", "BRAVE NEW WORLD", "TIME",0);
        ClypeData G = new FileClypeData("Euser", "hello.txt",0);

        System.out.println("Info for MessageClypeData: ");
        System.out.println("type = " + M.getType());
        System.out.println("Username = " + M.getUserName());
        System.out.println("hashcode: "+M.hashCode());
        //System.out.println(M.toString());
        System.out.println(M.getDate());
        System.out.println("data = "+F.getData());
        System.out.println("E's Data "+ E.getData());
        System.out.println("data = "+G.getData());


        //System.out.println("decrypt: "+ E.decrypt(E.encrypt("BRAVE NEW WORLD", "Time"),"TIME"));

        System.out.println("Info for FileClypeData:");
        System.out.println("type = " + F.getType());
        System.out.println("Username = " + F.getUserName());
        //System.out.println("hashcode: "+F.hashCode());
        System.out.println(F.getDate());
        System.out.println("data = "+F.getData());
        System.out.println(E.toString());



    }
}

