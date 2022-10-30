package com.example.demo.model;

import org.json.simple.JSONObject;

import java.util.Date;

import static com.example.demo.client.view.Util.FORMAT;

public class Message {
    private  String userName;
    private Date date;
    private String val;

    public Message(String val,String userName) {
        this.date = new Date();
        this.val = val;
        this.userName = userName;
    }

    public Message(JSONObject json) {
        this.date = new Date((Long) json.get("date"));
        this.val = (String) json.get("val");
        this.userName = (String) json.get("userName");
    }

    public JSONObject tojson(){
        JSONObject json = new JSONObject();
        json.put("date", this.date.getTime());
        json.put("val", this.val);
        json.put("userName", this.userName);

        return json;
    }


    @Override
    public  String toString() {
        return (this.userName + " [" + FORMAT(this.date) + "]:\n>> " + this.val + "\n");
    }



}