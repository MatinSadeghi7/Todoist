package com.example.matin.todoist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class WelcomeActivity extends AppCompatActivity {

    Button registerBtn;
    Button signInBtn;
    Button reconnectButton;
    Socket socket;
    TextView textView;
    Scanner scanner;
    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        reconnectButton = findViewById(R.id.reconnectbtn);
        reconnectButton.setVisibility(View.INVISIBLE);
        new DownloadFilesTask().execute("Somestring");
        textView = findViewById(R.id.textViewConnect);
        registerBtn = findViewById(R.id.registerbtn);
        signInBtn = findViewById(R.id.signinbtn);

//        try {
//            dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            dataInputStream = new DataInputStream(socket.getInputStream());
//            objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
//            objectInputStream = new ObjectInputStream(socket.getInputStream());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(WelcomeActivity.this , SigninActivity.class);
                //intent2.putExtra("mysocket", (Serializable)socket);
                startActivity(intent2);
                finish();
            }
        });


    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {


        protected Long doInBackground(String... params) {

            String param1 = params[0];

            try {
                //Log.v("=========>", "sadeghi");
                socket = new Socket("172.20.170.66", 5050);
                try {
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Log.v("=========>" , "matin");
                SocketSingelton.setSocket(socket);
            }
            catch (IOException e){
                Log.v("===========>" , e.getMessage());
                e.printStackTrace();
            }

            long someLong=0;
            int someInt=0;


            publishProgress(someInt);


            return someLong;
        }


        protected void onProgressUpdate(Integer... progress) {
        }


        protected void onPostExecute(Long result) {

        }
    }
}







