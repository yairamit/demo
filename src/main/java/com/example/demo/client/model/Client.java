package com.example.demo.client.model;

import com.example.demo.model.ChatRoom;

import java.io.*;
import java.net.Socket;


public class Client {
    private final String host="localhost";
    private final int port=8888;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private ChatRoom chatRoom;

    public Client( ){
        chatRoom= new ChatRoom();
        initBuffers();
    }

    private void initBuffers(){
        try {
            System.out.println("trying new client");

            socket=new Socket(host,port);


            bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        } catch (IOException e) {
            System.out.println("error");
        }
    }

    /**
     * send message to Server.
     *
     * @param msg text
     */
    public void sendMessage(String msg) {

        try {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * fetch updated chatroom from serer.
     *
     * @return string json of chatroom.
     */
    public String getData(){
        String input;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

}
