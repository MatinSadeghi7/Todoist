package com.example.matin.todoist;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SigninActivity extends AppCompatActivity {

    EditText useroremail;
    EditText pass;
    Button signinbtn;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        useroremail = findViewById(R.id.useroremailsgn);
        pass = findViewById(R.id.passsgn);
        signinbtn = findViewById(R.id.signinsgn);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useroremail1 = useroremail.getText().toString();
                String pass1 = pass.getText().toString();
                try {

                    dataOutputStream.writeUTF("signin");
                    dataOutputStream.writeUTF(useroremail1);
                    dataOutputStream.writeUTF(pass1);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
  }
 }

