package com.example.matin.todoist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    FloatingActionButton fb1 ;
    ListView listView;
    Socket socket = SocketSingelton.getSocket();
   //static ObjectInputStream objectInputStream;
    //static ObjectOutputStream objectOutputStream;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static ArrayList<Task> array = null;


//        try {
//            //dataInputStream = new DataInputStream(socket.getInputStream());
//           // dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        fb1 = findViewById(R.id.createNewTaskfb);
        listView = findViewById(R.id.listview);

        ArrayAdapter aat = new ArrayAdapter(this,R.layout.small_list_view,array);
        new DownloadFilesTask().execute();
        final Bundle bundle = getIntent().getExtras();
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle.getString("type").equals("green")){
                Intent intent = new Intent(TaskActivity.this , GreenUserActivity.class);
                startActivity(intent);
                }
                else if(bundle.getString("type").equals("silver")){
                    Intent intent = new Intent(TaskActivity.this , SilverActivity.class);
                    startActivity(intent);
                }
                else if(bundle.getString("type").equals("gold")){
                    Intent intent = new Intent(TaskActivity.this , GoldActivity.class);
                    startActivity(intent);
                }
            }
        });

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.activity_task, );


    }
    private class DownloadFilesTask extends AsyncTask<String, Integer, ArrayList<Task>> {

        // these Strings / or String are / is the parameters of the task, that can be handed over via the excecute(params) method of AsyncTask
        protected ArrayList<Task> doInBackground(String... params) {
            String str = null;

            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                //objectInputStream = new ObjectInputStream(socket.getInputStream());
               // objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
                Log.v("==================>>" , socket.toString());
               // Log.v("==================>>" , objectInputStream.toString());
                str =  dataInputStream.readUTF();

            } catch (IOException e) {
                e.printStackTrace();
            }


            // and so on...
            // do something with the parameters...
            // be careful, this can easily result in a ArrayIndexOutOfBounds exception
            // if you try to access more parameters than you handed over

            int someInt=0;

            // do something here with params
            // the params could for example contain an url and you could download stuff using this url here

            // the Integer variable is used for progress
            publishProgress(someInt);

            // once the data is downloaded (for example JSON data)
            // parse the data and return it to the onPostExecute() method
            // in this example the return data is simply a long value
            // this could also be a list of your custom-objects, ...
            return (ArrayList<Task>) array;
        }

        // this is called whenever you call puhlishProgress(Integer), for example when updating a progressbar when downloading stuff
        protected void onProgressUpdate(Integer... progress) {
        }

        // the onPostexecute method receives the return type of doInBackGround()
        protected void onPostExecute(ArrayList<Task> result) {
            array = result;


            // do something with the result, for example display the received Data in a ListView
            // in this case, "result" would contain the "someLong" variable returned by doInBackground();
        }
    }

    private ArrayList<Task> stringToArray(String str){
        ArrayList<Task> arr = new ArrayList<>();
        String[] tasks = str.split("$");
        for (int i = 0; i < tasks.length ; i++) {
            arr.add(stringToTask(tasks[i]));
        }
        return arr;
    }
    private Task stringToTask(String str){
        String[] properties = str.split("#");
        Task task=null;
        if (properties.length == 3 )
             task = new Task(properties[0] , properties[1] , properties[2]);
        else
            try {
                task = new Task(properties[0] , properties[1] , properties[2] ,new SimpleDateFormat("yyyy/MM/dd").parse(properties[3]),new Time(Integer.valueOf(properties[4])));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return task;
    }


}
