package com.example.demo.client;

import com.example.demo.server.ChatRoom;
import com.example.demo.view.ChatFrame;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public Client( String host,int port ){



        try {
            System.out.println("trying new client");

            socket=new Socket(host,port);


            bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        } catch (IOException e) {
            System.out.println("error");
        }


    }

    private void sendMessage(String msg) {

        try {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args){
        Client client = new Client("localhost",8888);
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client
                        String msg = client.bufferedReader.readLine();
                        System.out.println(msg);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                System.out.println("send your msg");
                while (true) {

                    // read the message to deliver.
                    Scanner scanner = new Scanner(System. in);

                    String msg =scanner.nextLine();
                    client.sendMessage(msg);
                    System.out.println(msg);


                }
            }
        });


        readMessage.start();
        sendMessage.start();
    }

}
