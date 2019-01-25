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

    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static ArrayList<Task> array = null;
    static String str1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        fb1 = findViewById(R.id.createNewTaskfb);
        listView = findViewById(R.id.listview);

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
    private class DownloadFilesTask extends AsyncTask<String, Integer, String> {


        protected String doInBackground(String... params) {
            String str = null;
//
//            try {
//                dataInputStream = new DataInputStream(socket.getInputStream());
//                dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                //objectInputStream = new ObjectInputStream(socket.getInputStream());
//               // objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
//                Log.v("==================>>" , socket.toString());
//               // Log.v("==================>>" , objectInputStream.toString());
//                str =  dataInputStream.readUTF();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            int someInt=0;


            publishProgress(someInt);


            return str;
        }


        protected void onProgressUpdate(Integer... progress) {
        }


        protected void onPostExecute(String result) {
            str1 = result;
           // ArrayAdapter aat = new ArrayAdapter(TaskActivity.this,R.layout.small_list_view,stringToArray(str1));


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
