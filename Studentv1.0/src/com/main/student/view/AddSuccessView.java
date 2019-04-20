package com.main.student.view;

import com.main.student.AppConstants;
import com.main.student.DAO;
import com.main.student.base.BaseDao;
import com.main.student.dao.StudentDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSuccessView extends JFrame {
    private JPanel jPanelAddSuccess; //JPanel面板
    private JButton addSuccessButton, continueAddButton, stopAddButton;	//JButton组件

    public AddSuccessView() {
        init();
    }

    private void init() {
        //		添加成功的面板
        jPanelAddSuccess = new JPanel();
        jPanelAddSuccess.setLayout(new GridLayout(3,1));
        addSuccessButton = new JButton(AppConstants.ADDVIEW_ADDSUCCESS);
        continueAddButton = new JButton(AppConstants.CONTINUE_ADDBUTTON);
        jPanelAddSuccess.add(addSuccessButton);
        continueAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddView();
            }
        });
        jPanelAddSuccess.add(continueAddButton);
        stopAddButton = new JButton(AppConstants.STOP_ADDBUTTON);
        stopAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanelAddSuccess.add(stopAddButton);
        this.add(jPanelAddSuccess,BorderLayout.NORTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//设置窗口默认关闭操作
        setBounds(470, 200, 400, 270);
        setResizable(false);	//true时用户可以自行改变窗口大小，false时不能改变
        setVisible(true);	//使控件显示出来，若已经显示出来则显示在窗口的最前方
    }
}
