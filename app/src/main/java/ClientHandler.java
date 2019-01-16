import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

 class ClientHandler implements Runnable {
     private Socket clientSocket;
     public DataInputStream inputStream;
     public DataOutputStream outputStream;
     private String clientID;

     public ClientHandler(Socket clientSocket, String clientID) throws IOException {
         this.clientID = clientID;
         this.clientSocket = clientSocket;
         this.inputStream = new DataInputStream(clientSocket.getInputStream());
         this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
     }


    public void run(){




    }
}
