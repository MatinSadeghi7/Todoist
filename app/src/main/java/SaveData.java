import com.example.matin.todoist.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class SaveData {

        public static List<User> readData() {
            ObjectInputStream objectInputStream;
            try {
                FileInputStream file = new FileInputStream("hw.ser");
                objectInputStream = new ObjectInputStream(file);
                Server.users = ((ArrayList<User>) objectInputStream.readObject());

                return Server.users;

            } catch (Exception e) {
                System.out.println("file is empty");
            }
            return Server.users;

        }
        public static void saveData() {

            try {
                FileOutputStream file = new FileOutputStream("hw.ser");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
                objectOutputStream.writeObject(Server.users);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


