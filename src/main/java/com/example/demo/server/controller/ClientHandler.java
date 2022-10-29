package com.example.demo.server.controller;

import com.example.demo.server.model.Message;
import com.example.demo.server.model.Server;

import java.io.*;
import java.net.Socket;
//import org.json.simple.*;

public class ClientHandler implements  Runnable {

    private     Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
             bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
             bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }catch(IOException e){

        }


    }



    @Override
    public void run() {
            while (!socket.isClosed()){
                String out;
                try {
                    out = bufferedReader.readLine();
                    Message msg = new Message(out);
                    Server.INSTANCE.getChatRoom().addMsgs(msg);
                    System.out.println(out);
                    out += "server answer";
                    bufferedWriter.write(out);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


//                Server.outut(out);
            }
    }

}
