package com.example.demo.client.view;



import com.example.demo.client.model.Client;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ChatFrame {


    String appName = "Chat Room";
    JFrame newFrame = new JFrame(appName);
    JButton sendMessage;
    JTextField messageBox;
    JTextArea chatBox;
    JTextField usernameChooser;
    JFrame loginFrame;
    private Client client;

    private ChatRoom chatRoom;

    public static void start(){
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ChatFrame mainGUI = new ChatFrame();
            mainGUI.preDisplay();
        });
    }

    private void preDisplay() {
        newFrame.setVisible(false);
        loginFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        JLabel chooseUsernameLabel = new JLabel("Pick a username:");
        JButton enterServer = new JButton("Enter Chat Server");
        enterServer.addActionListener(new enterServerButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        loginFrame.add(prePanel, BorderLayout.CENTER);
        loginFrame.add(enterServer, BorderLayout.SOUTH);
        loginFrame.setSize(300, 300);
        loginFrame.setVisible(true);

    }

    private void display() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setVisible(true);
    }

    private class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (!messageBox.getText().isEmpty()) {
                sendMessageToServer(messageBox.getText());
                sendMessageToChat(messageBox.getText());

                messageBox.setText("");
                messageBox.requestFocusInWindow();
            }
        }
    }

   private void updateChat(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               while (true) {
                   try {
                       JSONParser parser = new JSONParser();
                       JSONObject jsonObject = (JSONObject) parser.parse(client.getData());
                       chatRoom.update(jsonObject);
                       buildMessages(chatRoom.getMessages());
                   }catch (Exception e){

                   }




                   }
               }
       }).start();
   }
    private void sendMessageToChat(String text) {
        chatRoom.addMessage(new Message(text, username));
        buildMessages(chatRoom.getMessages());
    }

    private void sendMessageToServer(String text) {
        Message message =new Message(text, username);
        client.sendMessage((message.tojson()).toString());

    }

    public void buildMessages(List<Message> messageList){
        chatBox.setText("");
        messageList.forEach( message -> chatBox.append(message.toString()));
    }

    String  username;

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            newFrame.setTitle(username + "'s chat");
            ///
            if (username.length() < 1) {
                System.out.println("please choose your name again!");
            } else {
                client=new Client();
                chatRoom=new ChatRoom();
                updateChat();
                loginFrame.setVisible(false);
                display();
            }
        }

    }
}
