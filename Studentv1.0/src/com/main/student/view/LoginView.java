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
import com.main.student.dao.AdminDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 模块说明： 登录界面
 * 
 */
public class LoginView extends JFrame {

	private static final long serialVersionUID = -5278598737087831336L;
	private JPanel jPanelCenter, jPanelSouth;
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton, resetButton;

	public LoginView() {
		init();
	}

	private void init() {
		this.setTitle("Login");

		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_USERNAME));
		username = new JTextField();
		jPanelCenter.add(username);
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
		password = new JPasswordField();
		// enter key listener
		password.addKeyListener(new LoginListener());
		jPanelCenter.add(password);
		jPanelCenter.add(new JLabel("----------------------------------------------"));
		jPanelCenter.add(new JLabel("----------------------------------------------"));

		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		loginButton = new JButton(AppConstants.LOGIN);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		jPanelSouth.add(loginButton);
		resetButton = new JButton(AppConstants.RESET);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		jPanelSouth.add(resetButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(450, 250, 375, 140);
		this.setResizable(false);
		this.setVisible(true);
	}

	private class LoginListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				check();
			}
		}
	}

	private void check() {
		AdminDao adminDAO = (AdminDao) BaseDao.getAbilityDao(DAO.AdminDAO);
		if (adminDAO.queryForLogin(username.getText(), String.valueOf(password.getPassword()))) {
			dispose();
			new MainView();
		} else {
			username.setText("");
			password.setText("");
		}
	}

}
