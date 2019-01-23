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
//        final Bundle bundle = getIntent().getExtras();
//        fb1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(bundle.getString("type").equals("green")){
//                Intent intent = new Intent(TaskActivity.this , GreenUserActivity.class);
//                startActivity(intent);
//                }
//                else if(bundle.getString("type").equals("silver")){
//                    Intent intent = new Intent(TaskActivity.this , SilverActivity.class);
//                    startActivity(intent);
//                }
//                else if(bundle.getString("type").equals("gold")){
//                    Intent intent = new Intent(TaskActivity.this , GoldActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.activity_task, );


    }


}
