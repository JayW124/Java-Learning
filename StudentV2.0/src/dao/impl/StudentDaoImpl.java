package dao.impl;

import dao.StudentDao;
import model.PageBean;
import model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

/**
 * Created by wangjie on 2019/7/17
 * Descriptions:
 */
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Student> queryAllStudent() {
        try {
            String sql = "select * from student";
            List<Student> list = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student findStuById(int id) {
        String sql = "select * from student where id = ?";
        Student student = template.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class),id);
        return student;
    }

    @Override
    public void updateStudentInfo(Student stu) {
        try {
            String sql = "update student set name = ?, sno = ?, department = ?, hometown = ?, mark = ?, " +
                    "email = ?, tel = ?, sex = ? where id = ?";
            template.update(sql,stu.getName(),stu.getSno(),stu.getDepartment(),stu.getHometown(),
                    stu.getMark(),stu.getEmail(),stu.getTel(),stu.getSex(),stu.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delStuById(int id) {
        String sql = "delete from student where id=?";
        template.update(sql,id);
    }

    @Override
    public void addStu(Student stu) {
        try {
            String sql = "insert into student values(null,?,?,?,?,?,?,?,?)";
            template.update(sql,stu.getName(),stu.getSno(),stu.getDepartment(),
                    stu.getHometown(),stu.getMark(),stu.getEmail(),stu.getTel(),stu.getSex());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findByPage(int start, int rows) {
        List<Student> list = null;
        try {
            String sql = "select * from student limit ?,?";
            list = template.query(sql, new BeanPropertyRowMapper<>(Student.class), start, rows);
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from student";
        Integer totalCount = template.queryForObject(sql, Integer.class);
        return totalCount;
    }
}
