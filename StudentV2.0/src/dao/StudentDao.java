package dao;

import model.PageBean;
import model.Student;

import java.util.List;

/**
 * Created by wangjie on 2019/7/17
 * Descriptions:
 */
public interface StudentDao {
    public List<Student> queryAllStudent();
    public void updateStudentInfo(Student stu);
    public Student findStuById(int id);
    public void delStuById(int id);
    public void addStu(Student stu);
    public List<Student> findByPage(int start, int rows);
    public int findTotalCount();
}
