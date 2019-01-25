package com.example.matin.todoist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SigninActivity extends AppCompatActivity {

    EditText useroremail;
    EditText pass;
    Button signinbtn;
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;
    TextView error;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        useroremail = findViewById(R.id.useroremailsgn);
        pass = findViewById(R.id.passsgn);
        signinbtn = findViewById(R.id.donesign);
        error = findViewById(R.id.errorsgn);
        socket = SocketSingelton.getSocket();

        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }



//        signinbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String useroremail1 = useroremail.getText().toString();
//                String pass1 = pass.getText().toString();
//                try {
//
//                    dos.writeUTF("signin");
//                    dos.writeUTF(useroremail1);
//                    dos.writeUTF(pass1);
//                    dos.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


signinbtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        String usernameoremail = useroremail.getText().toString();
        String pass1 = pass.getText().toString();

        new DownloadFilesTask().execute( usernameoremail,pass1);

//        Intent intent = new Intent(SigninActivity.this, TaskActivity.class);
//        startActivity(intent);
        }
        });

        }
private class DownloadFilesTask extends AsyncTask<String, Integer, String> {

    // these Strings / or String are / is the parameters of the task, that can be handed over via the excecute(params) method of AsyncTask
    protected String doInBackground(String... params) {
        String result = null;

//        String param1 = params[0];

        try {
            Log.v("==========>asma", "inja signin");
            try{
                dos.writeUTF("login");
                dos.flush();
            }
            catch (Exception e){
                Log.v("==========>", e.toString());
            }
//            dos.writeUTF("register");
//            Log.v("==========>asma", "inja 2");
//            dos.flush();
//            Log.v("==========>asma", "unja");

            Log.v("paraaaaaam0" , params[0]);
            Log.v("paraaaaaam1" , params[1]);

            dos.writeUTF(params[0]);
            dos.flush();
            dos.writeUTF(params[1]);
            dos.flush();

             String status = dis.readUTF();
             Log.v("statuuuuuussss", status);
             if (status.equals("ok")){
                 String type1 = dis.readUTF();
                 Log.v("typeeeeeeeeeeeeeeee",type1);
                 result = status +"@"+ type1;
                    return  result;

             }else if (status.equals("notok")){

                 result = "notok";
                 return result;
             }


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
        return result;
    }

    // this is called whenever you call puhlishProgress(Integer), for example when updating a progressbar when downloading stuff
    protected void onProgressUpdate(Integer... progress) {
    }

    // the onPostexecute method receives the return type of doInBackGround()
    protected void onPostExecute(String result) {
        String [] array = result.split("@",2);

        if (array[0].equals("ok")){
            Intent intent = new Intent(SigninActivity.this, TaskActivity.class);

           intent.putExtra("type",array[1]);
            startActivity(intent);
        }else if (result.equals("notok")){
            error.setText("incorrect information");

        }
        // do something with the result, for example display the received Data in a ListView
        // in this case, "result" would contain the "someLong" variable returned by doInBackground();
    }
}
}

