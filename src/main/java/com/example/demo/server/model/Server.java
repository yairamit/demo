package com.example.demo.server.model;

import com.example.demo.server.controller.ClientHandler;
import com.example.demo.server.ChatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static public Server INSTANCE = new Server(8888);
    private ServerSocket serverSocket=null;

    private static ArrayList<ClientHandler> clientHandlers= new ArrayList<>();
    private Socket socket;
    public static int usersCount=0;
    ChatRoom chatRoom = new ChatRoom();

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    private Server(int port) {
        try {
            System.out.println("trying new server");
            serverSocket=new ServerSocket(port);


            while (!(serverSocket.isClosed())){

                socket=serverSocket.accept();
                usersCount++;

                ClientHandler clientHandler =new ClientHandler(socket);
                        Thread thread = new Thread(clientHandler);
                thread.start();
               clientHandlers.add(clientHandler);

            }

        }catch (IOException e) {

        }

    }



    public  void closeServer(){

    }
    public static void main(String[] args){
        Server srv = Server.INSTANCE;
//        Server server = new Server(8888);
    }
}
