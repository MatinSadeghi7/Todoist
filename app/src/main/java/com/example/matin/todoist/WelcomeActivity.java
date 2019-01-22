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
            }
        });


    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

        // these Strings / or String are / is the parameters of the task, that can be handed over via the excecute(params) method of AsyncTask
        protected Long doInBackground(String... params) {

            String param1 = params[0];

            try {
                //Log.v("=========>", "sadeghi");
                socket = new Socket("172.20.126.178", 5050);
                Log.v("=========>" , "matin");
                SocketSingelton.setSocket(socket);
            }
            catch (IOException e){
                Log.v("===========>" , e.getMessage());
                e.printStackTrace();
            }
            // and so on...
            // do something with the parameters...
            // be careful, this can easily result in a ArrayIndexOutOfBounds exception
            // if you try to access more parameters than you handed over

            long someLong=0;
            int someInt=0;

            // do something here with params
            // the params could for example contain an url and you could download stuff using this url here

            // the Integer variable is used for progress
            publishProgress(someInt);

            // once the data is downloaded (for example JSON data)
            // parse the data and return it to the onPostExecute() method
            // in this example the return data is simply a long value
            // this could also be a list of your custom-objects, ...
            return someLong;
        }

        // this is called whenever you call puhlishProgress(Integer), for example when updating a progressbar when downloading stuff
        protected void onProgressUpdate(Integer... progress) {
        }

        // the onPostexecute method receives the return type of doInBackGround()
        protected void onPostExecute(Long result) {
            // do something with the result, for example display the received Data in a ListView
            // in this case, "result" would contain the "someLong" variable returned by doInBackground();
        }
    }
}







