package com.jakey.motivateme.models;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Event extends SugarRecord<Event> {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private String name;
    private String date;

    public Event() {
    }

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Event(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.name = obj.getString("name");
        this.date = obj.getString("date");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("id", this.id);
        obj.put("name", this.name);
        obj.put("date", this.date);
        return obj;
    }
}
