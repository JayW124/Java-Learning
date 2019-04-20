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
 * 模块说明： 添加学生
 * 
 */
public class AddView extends JFrame {
	//这里为什么要序列化？？不写不会报错？
	private static final long serialVersionUID = -1984182788841566838L;
	//成员变量会自动初始化为默认值
	private JPanel jPanelCenter, jPanelSouth,jPanelAddSuccess; //JPanel面板
	private JButton addButton, exitButton,addSuccessButton;	//JButton组件
	private JTextField name, sno, department, hometown, mark, email, tel, sex; //JTextField文本框

	public AddView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.ADDVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_NAME)); //添加姓名label
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
		sno = new JTextField();
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
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton(AppConstants.ADDVIEW_ADDBUTTON);


		addButton.addActionListener(new ActionListener() { //添加监听内部类并重写方法
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDao) BaseDao.getAbilityDao(DAO.StudentDAO)).add(stu);
					System.out.println(isSuccess);
					if (isSuccess) {
						System.out.println("Add success");
						addSuccess();
						System.out.println("Continue adding");
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((StudentDao) BaseDao.getAbilityDao(DAO.StudentDAO))
								.list(MainView.currPageNum);
						MainView.initJTable(MainView.jTable, result);
					} else {
						addFailed();
						setEmpty();
					}
				}
			}
		});

		jPanelSouth.add(addButton);
		exitButton = new JButton(AppConstants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); //监听到行为后，释放此窗口，可以用pack或show恢复
			}
		});
		jPanelSouth.add(exitButton);
		//这两行有什么用,将jPanelCenter和jPanelSouth（添加学生信息的面板）添加到面板上
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//设置窗口默认关闭操作
		setBounds(470, 200, 400, 270);
		setResizable(false);	//true时用户可以自行改变窗口大小，false时不能改变
		setVisible(true);	//使控件显示出来，若已经显示出来则显示在窗口的最前方
	}

	private void addFailed() {
		new AddFailed();
	}

	/**
	 * @return 检查输入的学生信息字段中是否有空字段
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

	private void buildStudent(Student stu) {
		stu.setDepartment(department.getText());
		stu.setEmail(email.getText());
		stu.setHometown(hometown.getText());
		stu.setMark(mark.getText());
		stu.setName(name.getText());
		stu.setSno(sno.getText());
		stu.setTel(tel.getText());
		stu.setSex(sex.getText());
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

	private void addSuccess() {
		dispose();
		new AddSuccessView();
	}
}
