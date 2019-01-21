package com.example.matin.todoist;

import android.app.TimePickerDialog;
import android.content.Context;
import android.database.ContentObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class GreenUserActivity extends AppCompatActivity {
    Button time;
    int hour;
    int minute;
    Button date;
    int year;
    int month;
    int day;
    Context contextTime = this;
    Context contextDate = this;
    TextView showdate;
    TextView showtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_user);

        time = findViewById(R.id.timeButtonGreen);
        date = findViewById(R.id.dateButtonGreen);
        showdate = findViewById(R.id.showDate);
        showtime = findViewById(R.id.ShowTime);



        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(contextTime, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showtime.setText(hourOfDay + ":" + minute);


                    }
                },hour , minute , android.text.format.DateFormat.is24HourFormat(contextTime));
                tpd.show();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
