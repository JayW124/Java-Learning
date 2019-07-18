package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.PageBean;
import model.Student;
import service.StudentService;

import java.util.List;

/**
 * Created by wangjie on 2019/7/17
 * Descriptions:
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao dao = new StudentDaoImpl();

    @Override
    public List<Student> queryAllStudent() {
        return dao.queryAllStudent();
    }

    @Override
    public void updateStudentInfo(Student stu) {
        dao.updateStudentInfo(stu);
    }

    @Override
    public Student findStuById(int id) {
        return dao.findStuById(id);
    }

    @Override
    public void delStuById(int id) {
        dao.delStuById(id);
    }

    @Override
    public void addStu(Student stu) {
        dao.addStu(stu);
    }

    @Override
    public PageBean findByPage(String _curPage, String _rows) {
        int curPage = Integer.parseInt(_curPage);
        int rows = Integer.parseInt(_rows);
        PageBean<Student> pb = new PageBean<>();
        pb.setCurPage(curPage);
        pb.setRows(rows);
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);
        int start = (curPage - 1) * rows;
        List<Student> list = dao.findByPage(start, rows);
        pb.setList(list);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
