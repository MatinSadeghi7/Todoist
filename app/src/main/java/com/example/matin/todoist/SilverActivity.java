package com.example.matin.todoist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;

public class SilverActivity extends AppCompatActivity {
Button time1;
Button date1;
Button create;
TextView showdate1;
TextView showtime1;
int year;
int month;
int hour;
int minute;
int day;
Context contextTime = this;
Context contextDate = this;
SeekBar priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silver);
        time1 = findViewById(R.id.timeButtonSilver);
        date1 = findViewById(R.id.DateButtonSilver);
        showdate1 = findViewById(R.id.showDateSilver);
        showtime1 = findViewById(R.id.showTimeSilver);
        priority = findViewById(R.id.seekBar);
        create = findViewById(R.id.createsilver);





        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(contextTime, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showtime1.setText(hourOfDay + ":" + minute);


                    }
                },hour , minute , android.text.format.DateFormat.is24HourFormat(contextTime));
                tpd.show();
            }
        });
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(contextDate, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        showdate1.setText(year + "/" + month + "/" + dayOfMonth);
                    }
                },year , month , day);
                datePickerDialog.show();

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SilverActivity.this , TaskActivity.class);
                startActivity(intent);
            }

        });

    }
    }

