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

public class RegisterActivity extends AppCompatActivity {

    EditText name;
    EditText familyname;
    EditText username;
    EditText email;
    EditText pass;
    RadioButton gold;
    RadioButton silver;
    RadioButton green;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.namergs);
        familyname = findViewById(R.id.familyrgs);
        username = findViewById(R.id.userrgs);
        email = findViewById(R.id.emailrgs);
        pass = findViewById(R.id.passrgs);
        gold = findViewById(R.id.gold);
        silver = findViewById(R.id.silver);
        green = findViewById(R.id.green);
        register = findViewById(R.id.registerrgs);


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
                    name.setError("name is empty");
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
                String familyname1 = familyname.getText().toString();

                User user = new User(name1,familyname1,username1,pass1,email1);

//                Intent intent = new Intent(RegisterActivity.this, TaskActivity.class);
//                startActivity(intent);
            }
        });







    }
}
