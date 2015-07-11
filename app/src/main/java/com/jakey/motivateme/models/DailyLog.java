package com.jakey.motivateme.models;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyLog extends SugarRecord<DailyLog> {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private String date = DATE_FORMAT.format(new Date());
    private Integer weight;
    private String diet;
    private Boolean workout;

    public DailyLog() {
    }


    public DailyLog(Integer weight, String diet, Boolean workout) {
        this.weight = weight;
        this.diet = diet;
        this.workout = workout;
    }

    public static DailyLog findByDate(Date date) {
        List<DailyLog> dailyLogs = DailyLog.find(DailyLog.class, "date = ?", DATE_FORMAT.format(date));
        if (dailyLogs.size() > 0) {
            return dailyLogs.get(0);
        } else {
            return null;
        }
    }

    public void saveOrUpdate() {
        List<DailyLog> dailyLogs = DailyLog.find(DailyLog.class, "date = ?", this.date);
        if (dailyLogs.size() > 0) {
            this.setId(dailyLogs.get(0).getId());
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
}
