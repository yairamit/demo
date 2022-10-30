package com.example.demo.server.controller;

import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.Socket;
//import org.json.simple.*;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private static ChatRoom chatRoom;

    private String userName;

    public ClientHandler(Socket socket, ChatRoom chatRoom) {
        this.socket = socket;
        this.chatRoom = chatRoom;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
        }

    }


    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                getNextMessage();
                updateUsers();
            } catch (Exception ignored) {
            }
        }
    }

    public void getNextMessage() {
        String input;
        try {
            /* read line */
            input = bufferedReader.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(input);

            /* json to Message */
            Message message = new Message(jsonObject);
            chatRoom.addMessage(message);
        } catch (Exception e) {
        }
    }

    private void updateUsers() {
        for (ClientHandler client : chatRoom.getClients()) {
            if (client != this) {
                try {
                    client.bufferedWriter.write(chatRoom.toJson().toJSONString());
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
