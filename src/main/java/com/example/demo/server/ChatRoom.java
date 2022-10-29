package com.example.demo.server;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.server.controller.ClientHandler;
import com.example.demo.server.model.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ChatRoom {
    private List<Message> messages = new ArrayList<>();
    private List<ClientHandler> clients = new ArrayList<>();

    public ChatRoom() {
    }

    public void update(JSONObject json) {
        messages.clear();

        JSONArray msgListJson = (JSONArray) json.get("msgs");
        for(Object jsonMsg: msgListJson) {
           messages.add(new Message((JSONObject) jsonMsg));
        }
    }
    public void addMsgs(Message msg) {
        this.messages.add(msg);
    }

    public void toJson(){
        JSONArray msgsJson = new JSONArray();
        for (Message msg: messages) {
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