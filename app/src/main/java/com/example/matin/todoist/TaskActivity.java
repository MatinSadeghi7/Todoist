package com.example.matin.todoist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskActivity extends AppCompatActivity {

    FloatingActionButton fb1 ;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        fb1 = findViewById(R.id.createNewTaskfb);
        listView = findViewById(R.id.listview);

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this , GreenUserActivity.class);
                startActivity(intent);
            }
        });

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.activity_task, );


    }


}
