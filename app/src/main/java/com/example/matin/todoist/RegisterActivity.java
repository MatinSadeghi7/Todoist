package com.example.matin.todoist;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class RegisterActivity extends AppCompatActivity {

    EditText name;
    EditText lastName;
    EditText username;
    EditText email;
    EditText pass;
    RadioButton gold;
    RadioButton silver;
    RadioButton green;
    Button register;
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        socket = SocketSingelton.getSocket();
        Log.v("=========" , socket.toString());

        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = findViewById(R.id.namergs);
        lastName = findViewById(R.id.familyrgs);
        username = findViewById(R.id.userrgs);
        email = findViewById(R.id.emailrgs);
        pass = findViewById(R.id.passrgs);
        gold = findViewById(R.id.gold);
        silver = findViewById(R.id.silver);
        green = findViewById(R.id.green);
        register = findViewById(R.id.registerrgs);
        error = findViewById(R.id.errorrgs);


        username.addTextChangedListener(new TextWatcher() {
                @Override


                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


 }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    username.setError("user is empty");
                   // register.setVisibility(View.INVISIBLE);
                }
                if (Character.isDigit(s.toString().charAt(0))){
                    username.setError("the user can't start with number");
                    // register.setVisibility(View.INVISIBLE);
                }

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    email.setError("email is empty");
                }
                boolean flag = false;
                for (int i = 0; i <s.length() ; i++) {
                    if (s.toString().charAt(i) == '@' && s.toString().contains(".") ) {
                        flag = true;
                    }

                    }if (flag==false){
                        email.setError("the format of email isn't true");

                        }
                    }

        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    pass.setError("password is empty.");
                    //register.setVisibility(View.INVISIBLE);
                }
                if (s.length()<8 && s.length()!=0){
                    pass.setError("password is not strong enough.");
                    //register.setVisibility(View.INVISIBLE);
                }
                boolean hasLower = false;
                boolean hasUpper = false;
                boolean hasNumber = false;

                for(int i= 0; i < s.length();i++){
                    char ch = s.charAt(i);
                    if(Character.isLowerCase(ch)){
                        hasLower = true;
                    }
                    if (Character.isUpperCase(ch)){
                        hasUpper = true;
                    }
                    if(Character.isDigit(ch)){
                        hasNumber = true;
                    }
                }
                if(!(hasLower && hasNumber && hasUpper)){
                    pass.setError("password should involve at least a capital character, a small character and a digit.");
                    //register.setVisibility(View.INVISIBLE);
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    name.setError("name is empty.");
                    //register.setVisibility(View.INVISIBLE);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String username1 = username.getText().toString();
                String pass1 = pass.getText().toString();
                String email1 = email.getText().toString();
                String lastName1 = lastName.getText().toString();
                String type = null;


                if (gold.isChecked()) {
                    type = "gold";
                } else if (silver.isChecked()) {
                    type = "silver";
                } else if (green.isChecked()) {
                    type = "green";
                }


                if (name1.equals(null) || username1.equals(null) || pass1.equals(null) || email1.equals(null)) {
                    Log.v("asma","asmaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    error.setText("information is not complete.");
                } else if(!(name1.equals(null) || username1.equals(null) || pass1.equals(null) || email1.equals(null))){
                    new DownloadFilesTask().execute(name1, lastName1, email1, username1, pass1, type);
                    Intent intent = new Intent(RegisterActivity.this, TaskActivity.class);
                    //intent.putExtra("type",type);
                    startActivity(intent);
                }
            }
        });







    }
    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

        // these Strings / or String are / is the parameters of the task, that can be handed over via the excecute(params) method of AsyncTask
        protected Long doInBackground(String... params) {
            Log.v("param0" , params[0]);
            Log.v("param1" , params[1]);
            Log.v("param2" , params[2]);
            Log.v("param3" , params[3]);
            Log.v("param4" , params[4]);
            Log.v("param5" , params[5]);


            try {
                Log.v("==========>asma", "inja");
                try{
                    dos.writeUTF("register");
                    dos.flush();
                }
                catch (Exception e){
                    Log.v("==========>", e.toString());
                }

                Log.v("==========>asma", "inja 2");



                dos.writeUTF(params[0]);
                dos.flush();
                dos.writeUTF(params[1]);
                dos.flush();

                dos.writeUTF(params[2]);
                dos.flush();
                dos.writeUTF(params[3]);
                dos.flush();
                dos.writeUTF(params[4]);
                dos.flush();
                dos.writeUTF(params[5]);
                dos.flush();
//                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // and so on...
            // do something with the parameters...
            // be careful, this can easily result in a ArrayIndexOutOfBounds exception
            // if you try to access more parameters than you handed over

            long someLong=0;
            int someInt=0;
            //passAA222
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
