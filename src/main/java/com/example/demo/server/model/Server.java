package com.example.demo.server.model;

import com.example.demo.model.ChatRoom;
import com.example.demo.server.controller.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

//    static public Server INSTANCE = new Server(8888);
    private ServerSocket serverSocket=null;
    private int port;
//    private  ArrayList<ClientHandler> clientHandlers;
    private Socket socket;
    public static int usersCount=0;
    private ChatRoom chatRoom;

    public Server() {
        this.port = 8888;
        this.chatRoom =  new ChatRoom();
//        this.clientHandlers = new ArrayList<>();
    }

    public void start(){
        try {
            System.out.println("trying new server");
            serverSocket=new ServerSocket(port);


            while (!(serverSocket.isClosed())){

                socket=serverSocket.accept();
                usersCount++;

                ClientHandler clientHandler =new ClientHandler(socket, chatRoom);
                Thread thread = new Thread(clientHandler);
                thread.start();
                chatRoom.addClient(clientHandler);
//                clientHandlers.add(clientHandler);

            }

        }catch (IOException e) {

        }
    }
    public ChatRoom getChatRoom() {
        return chatRoom;
    }



    public  void closeServer(){

    }

}
