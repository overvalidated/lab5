package org.germanbeyger.lab5;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try ( Socket socket = new Socket(args[0], 34223)) {

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        } 
       
    }
}
