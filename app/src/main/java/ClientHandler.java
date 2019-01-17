import com.example.matin.todoist.User;

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


     public void run() {
         User user;

         boolean bool = true;
         while (bool) {

             String select = "";
             try {
                 select = inputStream.readUTF();
             } catch (IOException e) {
                 e.printStackTrace();
             }

             switch (select) {
                 case "register": {

                     try {
                         String username = inputStream.readUTF();
                         String lastname = inputStream.readUTF();
                         String email = inputStream.readUTF();
                         String pass = inputStream.readUTF();
                         String name = inputStream.readUTF();


                         for (int i = 0; i < Server.users.size(); i++) {
                             if (Server.users.get(i).getUsername().equals(username)) {
                                 throw new ExistingUserException();

                             }
                         }
                         Server.users.add(user = new User(username, pass, email));
                         //یجوری به کاربر نشون بده که ثبت نام شدی
                         SaveData.saveData();
                         break;
                     } catch (ExistingUserException e){
                         //بهش یگو قبلا ثبت نام کردی عزیزم

                 } catch(IOException e){
                     e.printStackTrace();
                 }

                break;
             }
                 case "login" : {


                     String useroremail = null;
                     String password = null;
                     try {
                         useroremail = inputStream.readUTF();
                          password = inputStream.readUTF();

                     } catch (IOException e) {
                         e.printStackTrace();
                     }


                     boolean flag = false;
                     for (int i = 0; i <Server.users.size() ; i++) {
                         if (Server.users.get(i).getUsername().equals(useroremail) || Server.users.get(i).getEmail().equals(useroremail)) {
                             flag = true;
                             if (Server.users.get(i).getPass().equals(password)) {
                                 user = Server.users.get(i);
                                 //وارد شدی برو صفحه اصلی
                             }
                         }

                     }

                 }

             }
         }
     }
 }
