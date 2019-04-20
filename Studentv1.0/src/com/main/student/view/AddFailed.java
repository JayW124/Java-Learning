package com.main.student.view;

import com.main.student.AppConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFailed extends JFrame {
    private JPanel jPanelAddFailed;
    private JButton addFailedButton, reAddButton;

    public AddFailed() {
        init();
    }

    private void init() {
        jPanelAddFailed = new JPanel();
        jPanelAddFailed.setLayout(new GridLayout(2,1));
        addFailedButton = new JButton(AppConstants.ADDVIEW_ADDFailed);
        jPanelAddFailed.add(addFailedButton);
        reAddButton = new JButton(AppConstants.READD_BUTTON);
        reAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new MainView();//这样会直接回到主页面
                dispose();
            }
        });
        jPanelAddFailed.add(reAddButton);
        this.add(jPanelAddFailed,BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//设置窗口默认关闭操作
        setBounds(470, 200, 200, 200);
        setResizable(false);	//true时用户可以自行改变窗口大小，false时不能改变
        setVisible(true);	//使控件显示出来，若已经显示出来则显示在窗口的最前方
    }
}
