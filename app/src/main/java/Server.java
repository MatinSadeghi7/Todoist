import com.example.matin.todoist.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<User> users = new ArrayList<>();
    static int client;
    public static void main(String[] args) throws IOException {
        users.addAll(SaveData.readData());
        Reader reader = new BufferedReader(new FileReader("ClientId"));
        client = reader.read();
        ServerSocket serverSocket = new ServerSocket(222);
        Socket clientSocket;
        SaveData.readData();
        try {
            while (true) {
                clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(clientSocket, "Client#" + client);
                Thread thread = new Thread(clientHandler);
                thread.start();
                client++;
                Writer writer=new BufferedWriter(new FileWriter("ClientId"));
                writer.write(client);
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
