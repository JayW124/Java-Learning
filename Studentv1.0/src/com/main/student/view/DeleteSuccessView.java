package com.main.student.view;

import com.main.student.AppConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSuccessView extends JFrame {
    private JPanel jPanelCenter;
    private JButton deleteSuccessButton, exitButton;

    public DeleteSuccessView() {
        init();
    }

    private void init() {
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(2,1));
        deleteSuccessButton = new JButton(AppConstants.DELETESUCCESSBUTTON);
        jPanelCenter.add(deleteSuccessButton);
        exitButton = new JButton(AppConstants.EXITBUTTON);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanelCenter.add(exitButton);
        this.add(jPanelCenter,BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470, 200, 400, 270);
        setResizable(false);	//true时用户可以自行改变窗口大小，false时不能改变
        setVisible(true);
    }
}
