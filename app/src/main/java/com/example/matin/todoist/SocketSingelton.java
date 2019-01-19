package com.example.matin.todoist;
import java.net.Socket;
public class SocketSingelton {
    private static Socket socket;
    public static void setSocket(Socket socketpass){
        SocketSingelton.socket = socketpass;
    }
    public static Socket getSocket(){
        return SocketSingelton.socket;
        //return socket;
    }
}
