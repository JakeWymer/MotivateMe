package com.jakey.motivateme.models;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LogRecord extends SugarRecord<LogRecord> {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private String date = DATE_FORMAT.format(new Date());
    private Integer weight;
    private String diet;
    private Boolean workout;

    public LogRecord() {
    }

    public LogRecord(Integer weight, String diet, Boolean workout) {
        this.weight = weight;
        this.diet = diet;
        this.workout = workout;
    }

    public static LogRecord findByDate(Date date) {
        List<LogRecord> logRecords = LogRecord.find(LogRecord.class, "date = ?", DATE_FORMAT.format(date));
        if (logRecords.size() > 0) {
            return logRecords.get(0);
        } else {
            return null;
        }
    }

    public void saveOrUpdate() {
        List<LogRecord> logRecords = LogRecord.find(LogRecord.class, "date = ?", this.date);
        if (logRecords.size() > 0) {
            this.setId(logRecords.get(0).getId());
        }
        this.save();
    }

    public Boolean getWorkout() {
        return workout;
    }

    public void setWorkout(Boolean workout) {
        this.workout = workout;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "date='" + date + '\'' +
                ", weight=" + weight +
                ", diet='" + diet + '\'' +
                ", workout=" + workout +
                '}';
    }
}
