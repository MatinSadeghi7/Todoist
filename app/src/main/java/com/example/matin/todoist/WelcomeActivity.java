package com.example.matin.todoist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    DataOutputStream dos;
    DataInputStream dis;
    Scanner scanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        reconnectButton = findViewById(R.id.reconnectbtn);
        reconnectButton.setVisibility(View.INVISIBLE);
        connecting();
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
                intent2.putExtra("Socket", (Serializable) socket);
                startActivity(intent2);
            }
        });

    }
 public boolean connecting() {
        try {
            socket = new Socket("localhost", 222);
        }
        catch (IOException e){
            textView.setText("problem.");
            reconnectButton.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
 }
}







