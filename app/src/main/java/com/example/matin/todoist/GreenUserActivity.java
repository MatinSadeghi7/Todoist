package com.example.matin.todoist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GreenUserActivity extends AppCompatActivity {
    Button time;
    int hour;
    int nowMinute;
    Button date;
    int year;
    int month;
    int day;
    int hourrr;
    int minutett;
    int yearrr;
    int monthhh;
    int dayyy;

    Context contextTime = this;
    Context contextDate = this;
    TextView showdate;
    TextView showtime;
    TextView title;
    TextView detail;
    RadioButton priority1;
    RadioButton priority2;
    RadioButton priority3;
    Button create ;
    Socket socket = SocketSingelton.getSocket();
    DataOutputStream dos;
    DataInputStream dis;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_user);

        time = findViewById(R.id.timeButtonGreen);
        date = findViewById(R.id.dateButtonGreen);
        showdate = findViewById(R.id.showDate);
        showtime = findViewById(R.id.ShowTime);
        title = findViewById(R.id.titleEditTextGreen);
        detail = findViewById(R.id.detailEditTextGreen);
        priority1 = findViewById(R.id.priority1Green);
        priority2 = findViewById(R.id.priority2Green);
        priority3 = findViewById(R.id.priority3Green);
        create = findViewById(R.id.createButtonGreen2);
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream (socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("===============>>>" , socket.toString());
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                nowMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(contextTime, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showtime.setText(hourOfDay + ":" + minute);
                        hourrr = hourOfDay;
                        minutett = minute;

                    }
//                },hour , minute , android.text.format.DateFormat.is24HourFormat(contextTime));
                },hour , nowMinute , false);
                tpd.show();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(contextDate, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      showdate.setText(year + "/" + month + "/" + dayOfMonth);
                      yearrr = year;
                      monthhh = month;
                      dayyy = dayOfMonth;

                    }
                },year , month , day);
                datePickerDialog.show();



            }


        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title1 = title.getText().toString();
                String detail1 = detail.getText().toString();
                String priority = null;

                if (priority1.isChecked()){
                    priority = "0";
                }else  if (priority2.isChecked()){
                    priority = "50";
                }else if (priority3.isChecked()){
                    priority = "100";
                }



                String year1 = Integer.toString(yearrr);
                String month1 = Integer.toString(monthhh);
                String day1 = Integer.toString(dayyy);
                String hour1 = Integer.toString(hourrr);
                String minute1 = Integer.toString(minutett);
                Log.v("teeeeeeeeeeeeeeeeeeest " , "maaaaaaaaaaaaaaaaaaat");

                new DownloadFilesTask().execute(title1 , detail1 , priority,year1,month1,day1,hour1,minute1);
                Intent intent = new Intent(GreenUserActivity.this , TaskActivity.class);
                startActivity(intent);

            }
        });
    }


    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {


        protected Long doInBackground(String... params) {
            Log.v("param0" , params[0]);
            Log.v("param1" , params[1]);
            Log.v("param2" , params[2]);
            Log.v("param3" , params[3]);
            Log.v("param4" , params[4]);
            Log.v("param5" , params[5]);
            Log.v("param6" , params[6]);
            Log.v("param7" , params[7]);


            try {


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
                dos.writeUTF(params[6]);
                dos.flush();
                dos.writeUTF(params[7]);
                dos.flush();
                String str = null;

                try {
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());

                    Log.v("==================>>" , socket.toString());

                    str =  dis.readUTF();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }



            long someLong=0;
            int someInt=0;


            publishProgress(someInt);


            return someLong;
        }


        protected void onProgressUpdate(Integer... progress) {
        }


        protected void onPostExecute(Long result) {



        }

        private ArrayList<Task> stringToArray(String str){
            ArrayList<Task> arr = new ArrayList<>();
            String[] tasks = str.split("$");
            for (int i = 0; i < tasks.length ; i++) {
                arr.add(stringToTask(tasks[i]));
            }
            return arr;
        }
        private Task stringToTask(String str){
            String[] properties = str.split("#");
            Task task=null;
            if (properties.length == 3 )
                task = new Task(properties[0] , properties[1] , properties[2]);
            else
                try {
                    task = new Task(properties[0] , properties[1] , properties[2] ,new SimpleDateFormat("yyyy/MM/dd").parse(properties[3]),new Time(Integer.valueOf(properties[4])));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            return task;
        }


        //public void execute(String title1, String detail1, String priority, int year, int month, int day, int hour, int minute) {
        }
    }

