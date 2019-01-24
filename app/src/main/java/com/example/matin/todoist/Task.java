package com.example.matin.todoist;

import java.sql.Time;
import java.util.Date;

public class Task  implements Comparable<Task>{
    private String title;
    private String detail;
    private Date date;
    public String priority;
    private Time time;

    Task(String title, String detail,  String priority,Date date, Time time){
        this.time = time;
        this.priority=priority;
        this.detail=detail;
        this.title=title;
        this.date=date;
    }

    public Task(String title, String detail, String priority) {
        this.title=title;
        this.detail=detail;
        this.priority=priority;
    }

    String getTitle(){
        return this.title;
    }
    String getDetail(){
        return this.detail;
    }
    Date getDate(){
        return this.date;
    }
    //String getPriority(){
    //  return this.priority;
    //}
    Time getTime(){
        return this.time;
    }

    private Date dateTime;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    @Override
    public int compareTo(Task t) {
        return getDateTime().compareTo(t.getDateTime());
    }
    public int getPrio(){
        return Integer.parseInt(priority);
    }
}

