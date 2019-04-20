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
public class UpdateView extends JFrame {
    private JPanel jPanelCenter, jPanelSouth;
    private JButton updateButton,exitButton;
    private JTextField name, sno, department, hometown, mark, email, tel, sex; //JTextField文本框

    public UpdateView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.UPDATEVIEW_TITLE);
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(9,2));
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_NAME));
        name = new JTextField();
        jPanelCenter.add(name);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
        sno = new JTextField();
        jPanelCenter.add(sno);
        jPanelCenter.add(sno);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SEX));
        sex = new JTextField();
        jPanelCenter.add(sex);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_DEPARTMETN));
        department = new JTextField();
        jPanelCenter.add(department);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_HOMETOWN));
        hometown = new JTextField();
        jPanelCenter.add(hometown);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_MARK));
        mark = new JTextField();
        jPanelCenter.add(mark);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_EMAIL));
        email = new JTextField();
        jPanelCenter.add(email);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_TEL));
        tel = new JTextField();
        jPanelCenter.add(tel);

        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1,2));
        updateButton = new JButton(AppConstants.UPDATEVIEW_UPDATEBUTTON);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete
                if (check()) {
                    Student stu = new Student();
                    buildStudent(stu);
                    boolean isSuccess = ((StudentDao) BaseDao.getAbilityDao(DAO.StudentDAO)).update(stu);
                    if (isSuccess) {
                        setEmpty();
                    }
                }
            }
        });

        jPanelSouth.add(updateButton);
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

    /**
     * @return 根据输入的学生信息是否有字段为空
     */
    private boolean check() {
        boolean result = false;
        if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(department.getText())
                || "".equals(sex.getText()) || "".equals(mark.getText()) || "".equals(tel.getText())
                || "".equals(email.getText()) || "".equals(hometown.getText())) {
            return result;
        } else {
            result = true;
        }
        return result;
    }

    public void buildStudent(Student stu) {
        stu.setDepartment(department.getText());
        stu.setTel(tel.getText());
        stu.setEmail(email.getText());
        stu.setHometown(hometown.getText());
        stu.setMark(mark.getText());
        stu.setName(name.getText());
        stu.setSex(sex.getText());
        stu.setSno(sno.getText());
    }

    private void setEmpty() {
        name.setText("");
        sno.setText("");
        department.setText("");
        sex.setText("");
        email.setText("");
        hometown.setText("");
        tel.setText("");
        mark.setText("");
    }
}
