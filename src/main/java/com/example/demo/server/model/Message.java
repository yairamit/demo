package com.example.demo.server.model;

import org.json.simple.JSONObject;

import java.util.Date;

public class Message {
    private Date date;
    private String val;

    public Message(String val) {
        this.date = new Date();
        this.val = val;
    }

    public Message(JSONObject json) {
        this.date = new Date((Long) json.get("date"));
        this.val = (String) json.get("val");
    }

    public JSONObject tojson(){
        JSONObject json = new JSONObject();
        json.put("date", this.date.getTime());
        json.put("val", this.val);

        return json;
    }


}
