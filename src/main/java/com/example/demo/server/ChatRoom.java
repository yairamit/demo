package com.example.demo.server;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.server.controller.ClientHandler;
import com.example.demo.server.model.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ChatRoom {
    private List<Message> msgs = new ArrayList<>();
    private List<ClientHandler> clients = new ArrayList<>();

    public ChatRoom() {
    }

    public void getMsgs(JSONObject json) {
        msgs.clear();

        JSONArray msgListJson = (JSONArray) json.get("msgs");
        for(Object jsonMsg: msgListJson) {
           msgs.add(new Message((JSONObject) jsonMsg));
        }
    }
    public void addMsgs(Message msg) {
        this.msgs.add(msg);
    }

    public void toJson(){
        JSONArray msgsJson = new JSONArray();
        for (Message msg:msgs) {
            msgsJson.add(msg.tojson());
        }

    }



}
/**
 * chatroom's json   <-
 * {"msgs": [
 *     {"date": 21312321, "val":  "hello yo"},
 *      {"date": 21312321, "val":  "hello yo"},
 *      {"date": 21312321, "val":  "hello yo"},
 *      {"date": 21312321, "val":  "hello yo"},
 *      {"date": 21312321, "val":  "hello yo"},
 *      {"date": 21312321, "val":  "hello yo"},
 * ]}
 *
 *
 *
 *
 *
 *
 *
 *
 */