/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student.view;

import com.main.student.AppConstants;
import com.main.student.DAO;
import com.main.student.base.BaseDao;
import com.main.student.dao.StudentDao;
import com.main.student.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 根据学号和姓名删除学生信息
 */
public class DeleteView extends JFrame {
    private JPanel jPanelCenter, jPanelSouth;
    private JButton deleteButton,exitButton;
    private JTextField sno; //JTextField文本框

    public DeleteView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.DELETEVIEW_TITLE);
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(9,2));
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
        sno = new JTextField();
        jPanelCenter.add(sno);

        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1,2));
        deleteButton = new JButton(AppConstants.DELETEVIEW_DELETEBUTTON);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete
                if (check()) {
                    String sno = DeleteView.this.sno.getText();
                    boolean isSuccess = ((StudentDao) BaseDao.getAbilityDao(DAO.StudentDAO)).delete(sno);
                    System.out.println(isSuccess);
                    if (isSuccess) {
                        deleteSuccess();
                        if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
                            MainView.currPageNum = 1;
                        }
                        String[][] result = ((StudentDao) BaseDao.getAbilityDao(DAO.StudentDAO))
                                .list(MainView.currPageNum);
                        MainView.initJTable(MainView.jTable, result);
                    }
                }
            }
        });

        jPanelSouth.add(deleteButton);
        exitButton = new JButton(AppConstants.EXITBUTTON);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanelSouth.add(exitButton);

        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470,200,400,270);
        setResizable(false);
        setVisible(true);
    }

    private void deleteSuccess() {
        dispose();
        new DeleteSuccessView();
    }

    /**
     * @return 根据输入的学生信息是否有字段为空
     */
    private boolean check() {
        boolean result = false;
        if ("".equals(sno.getText())) {
            return result;
        } else {
            result = true;
        }
        return result;
    }

    private void setEmpty() {
        sno.setText("");
    }
}
