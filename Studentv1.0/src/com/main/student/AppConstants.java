/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student;

/**
 * 模块说明： 常量
 * 
 */
public class AppConstants {
	// jdbc,java database connecitivity，java数据库连接，提供数据库的增删改查接口
	//这里的数据库是Sqlite数据库，需要修改成MySQL数据库
//	public static final String JDBC_URL = "jdbc:sqlite:Student.db";  //数据库名
	//加上末尾字段可以跳过ssl认证
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/Student";  //数据库名

//	public static final String JDBC_USERNAME = "root";  //数据库配置时用户名
	public static final String JDBC_USERNAME = "root";  //数据库配置时用户名

//	public static final String JDBC_PASSWORD = "root";  //数据库密码
	public static final String JDBC_PASSWORD = "root";  //数据库密码

//	public static final String JDBC_DRIVER = "org.sqlite.JDBC"; //连接数据库的驱动程序名
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //连接数据库的驱动程序名

	// student field
	public static final String STUDENT_NAME = "姓名";
	public static final String STUDENT_SNO = "学号";
	public static final String STUDENT_SEX = "性别";
	public static final String STUDENT_DEPARTMETN = "院系";
	public static final String STUDENT_HOMETOWN = "籍贯";
	public static final String STUDENT_EMAIL = "电子邮件";
	public static final String STUDENT_TEL = "联系方式";
	public static final String STUDENT_MARK = "学分";

	// login view
	public static final String LOGIN_TITLE = "登录界面";
	public static final String LOGIN_USERNAME = "用户名";
	public static final String LOGIN_PASSWORD = "密码";
	public static final String LOGIN = "登录";
	public static final String RESET = "重置";

	// main view
	public static final String MAINVIEW_TITLE = "学生信息管理系统";
	public static final String MAINVIEW_PAGENUM_JLABEL_DI = "第 ";
	public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/99 页";
	public static final String MAINVIEW_FIND_JLABEL = "查询结果";
	public static final String MAINVIEW_FIRST = "首页";
	public static final String MAINVIEW_LAST = "末页";
	public static final String MAINVIEW_PRE = "上一页";
	public static final String MAINVIEW_NEXT = "下一页";
	public static final String PARAM_FIND_CONDITION = "";
	public static final String PARAM_FIND = "查找";
	public static final String PARAM_ADD = "添加";
	public static final String PARAM_DELETE = "删除";
	public static final String PARAM_UPDATE = "更新";

	// add view
	public static final String ADDVIEW_TITLE = "添加学生信息";
	public static final String ADDVIEW_ADDBUTTON = "添加";
	public static final String ADDVIEW_ADDSUCCESS = "添加成功";
	public static final String ADDVIEW_ADDFailed = "添加失败";
	public static final String READD_BUTTON = "重新添加";
	public static final String CONTINUE_ADDBUTTON = "继续添加";
	public static final String STOP_ADDBUTTON = "停止添加";

	public static final String EXITBUTTON = "退出";

	// delete view
	public static final String DELETEVIEW_TITLE = "删除学生信息";
	public static final String DELETEVIEW_DELETEBUTTON = "删除";
	public static final String DELETESUCCESSBUTTON = "删除成功";


	// update view
	public static final String UPDATEVIEW_TITLE = "更新学生信息";
	public static final String UPDATEVIEW_UPDATEBUTTON = "更新";

}
