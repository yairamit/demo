package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.server.controller.ClientHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ChatRoom {
    private List<Message> messages = new ArrayList<>();
    private List<ClientHandler> clients = new ArrayList<>();

    public ChatRoom() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void update(JSONObject json) {
        messages.clear();

        JSONArray msgListJson = (JSONArray) json.get("messages");
        for(Object jsonMsg: msgListJson) {
           messages.add(new Message((JSONObject) jsonMsg));
        }

    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public void addClient(ClientHandler client){
        clients.add(client);
    }

    public void addMessage(Message msg) {
        this.messages.add(msg);
    }

    public JSONObject toJson(){
        JSONArray msgsJson = new JSONArray();
        for (Message msg: messages) {
            msgsJson.add(msg.tojson());
        }
        JSONObject chatJson = new JSONObject();
        chatJson.put("messages", msgsJson);

        return chatJson;
    }
}
