package com.example.demo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatFrame extends JFrame {
    private JButton sendButton;
    private JTextField textField1;
    public static String _msg = "";

    public ChatFrame(){
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _msg = textField1.getText();
                System.out.println(textField1.getText());
            }
        });
    }
}
