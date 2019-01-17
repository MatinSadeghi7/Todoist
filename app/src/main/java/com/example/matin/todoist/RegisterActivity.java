package com.example.matin.todoist;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.namergs);
        lastName = findViewById(R.id.familyrgs);
        username = findViewById(R.id.userrgs);
        email = findViewById(R.id.emailrgs);
        pass = findViewById(R.id.passrgs);
        gold = findViewById(R.id.gold);
        silver = findViewById(R.id.silver);
        green = findViewById(R.id.green);
        register = findViewById(R.id.registerrgs);

        socket = (Socket) getIntent().getSerializableExtra("Socket");

        System.out.println(socket.getPort());


        username.addTextChangedListener(new TextWatcher() {
                @Override


                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (Character.isDigit(s.toString().charAt(0))){
                        username.setError("the user can't start with number");
                    }

 }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    username.setError("user is empty");
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
                    if (s.toString().charAt(i) == '@' && s.toString().endsWith(".com") ) {
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
                if (s.length()==0){
                    pass.setError("pass is empty");
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
                try {
                    dos.writeUTF("register");
                    dos.writeUTF(name1);
                    dos.writeUTF(username1);
                    dos.writeUTF(pass1);
                    dos.writeUTF(email1);
                    dos.writeUTF(lastName1);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Intent intent = new Intent(RegisterActivity.this, TaskActivity.class);
//                startActivity(intent);
            }
        });







    }
}
