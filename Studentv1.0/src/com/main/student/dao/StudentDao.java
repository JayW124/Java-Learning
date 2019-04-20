/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student.dao;

import com.main.student.base.BaseDao;
import com.main.student.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息增删改查
 * execute：可执行所有数据库操作语句
 * executeQuery：执行select语句语句，返回ResultSet结果集
 * executeUpdate：执行insert、delete、update语句，返回值为整数标识影响的row，与MySQL返回类似
 * rs.next()：指针向下移动一行，若无下一行则为空，可以用于判断结果是否为null
 * rs.getString(String label)：获得label标签下的字段
 */
public class StudentDao extends BaseDao {
    public static StudentDao sd = null;
    private final int fieldNum = 9; //学生信息表中一共有9列
    private final int showNum = 15; //每页显示十五条学生信息
    //获取学生类的实例，为什么要同步？？防止并发死锁
    public static synchronized StudentDao getInstance() {
        if (sd == null) {
            sd = new StudentDao();
        }
        return sd;
    }

    /**
     * @param stu 学生对象实例
     * @return：是否成功更新学生信息
     */
    public boolean update(Student stu) {
        boolean result = false;
        try {
            //若学生对象为空或根据学号找不到学生信息，则返回为false
            if (stu == null) {
                return result;
            }
            if (queryBySno(stu.getSno()) == 0) {
                return result;
            }
            String sql = "update student set sex=?,department=?,email=?," +
                    "tel=?,hometown=?,mark=? where name=? and sno=?";
            String[] param = { stu.getSex(), stu.getDepartment(), stu.getEmail(),
                    stu.getTel(), stu.getHometown(), stu.getMark(), stu.getName(), stu.getSno() };
            int rowCount = db.executeUpdate(sql, param);
            if (rowCount == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destroy();  //卸载servlet容器
        }
        return result;
    }

    /**
     * @param sno
     * @return
     */
    public boolean delete (String sno) {
        boolean result = false;
        System.out.println("try");
        try {
            if (queryBySno(sno) == 0) {
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "delete from student where sno=?";
        String[] param = {sno};
        int rowCount = db.executeUpdate(sql,param);
        if (rowCount == 1) {
            result = true;
        }
        destroy();
        return result;
    }

    /**
     * @param stu
     * @return：是否成功添加学生信息，学号不存在时返回false
     */
    public boolean add(Student stu) {
        boolean result = false;
        if (stu == null) {
            return result;
        }
        try {
            if (queryBySno(stu.getSno()) == 1) {
                return result;
            }
            String sql = "insert into student(name,sno,sex,department,hometown,mark,email,tel)" +
                    " values(?,?,?,?,?,?,?,?)";
            String[] param = {stu.getName(), stu.getSno(), stu.getDepartment(),
                    stu.getHometown(),stu.getMark(),stu.getEmail(),stu.getTel(),stu.getSex()};
            int rowCount = db.executeUpdate(sql, param);
            if (rowCount == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destroy();
        }

        return result;
    }

    /**
     * @param name：学生姓名
     * @return：姓名中有相同字段的学生组成的数组
     */
    public String[][] queryByName(String name) {
        String[][] result = null;
        if (name.length() < 0) {
            return result;
        }
        List<Student> stus = new ArrayList<Student>();
        int i = 0;
        String sql = "select * from student where name like ?"; //like与%连用，表示存在相同字段
        String[] param = { "%" + name + "%" };
        rs = db.executeQuery(sql, param);
        try {
            while (rs.next()) {
                buildList(rs, stus, i);
                i++;
            }
            if (stus.size() > 0) {
                result = new String[stus.size()][fieldNum];
                for (int j = 0; j < stus.size(); j++) {
                    buildResult(result, stus, j);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            destroy();
        }

        return result;
    }

    /**
     * @param pageNum 页码
     * @return：该页学生信息组成的数组
     */
    public String[][] list(int pageNum) {
        String[][] result = null;
        if (pageNum < 1) {
            return result;
        }
        List<Student> stus = new ArrayList<Student>();
        int i = 0;
        int beginNum = (pageNum - 1) * showNum;
        String sql = "select * from student limit ?,?";
        Integer[] param = { beginNum, showNum };
        rs = db.executeQuery(sql, param);
        try {
            while (rs.next()) {
                buildList(rs, stus, i);
                i++;
            }
            if (stus.size() > 0) {
                result = new String[stus.size()][fieldNum];
                for (int j = 0; j < stus.size(); j++) {
                    buildResult(result, stus, j);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            destroy();
        }
        return result;
    }

    /**
     * @param rs 学生对象
     * @param list 学生对象列表
     * @param i
     * @throws SQLException
     */
    private void buildList(ResultSet rs, List<Student> list, int i) throws SQLException {
        Student stu = new Student();
        stu.setId(i + 1);
        stu.setName(rs.getString("name"));
        stu.setDepartment(rs.getString("department"));
        stu.setEmail(rs.getString("email"));
        stu.setHometown(rs.getString("hometown"));
        stu.setMark(rs.getString("mark"));
        stu.setSex(rs.getString("sex"));
        stu.setSno(rs.getString("sno"));
        stu.setTel(rs.getString("tel"));
        list.add(stu);
    }

    /**
     * @param result 学生信息组成的数组
     * @param stus
     * @param j
     */
    private void buildResult(String[][] result, List<Student> stus, int j) {
        Student stu = stus.get(j);
        result[j][0] = String.valueOf(stu.getId());
        result[j][1] = stu.getName();
        result[j][2] = stu.getSno();
        result[j][3] = stu.getSex();
        result[j][4] = stu.getDepartment();
        result[j][5] = stu.getHometown();
        result[j][6] = stu.getMark();
        result[j][7] = stu.getEmail();
        result[j][8] = stu.getTel();
    }

    /**
     * @param sno 学生学号
     * @return：返回0/1，学号为空或未找到该学号对应信息时返回0，找到则返回1
     * @throws SQLException
     */
    // query by sno
    private int queryBySno(String sno) throws SQLException {
        int result = 0;
        if ("".equals(sno) || sno == null) {
            System.out.println("sno");
            return result;
        }
        String checkSql = "select * from student where sno=?";
        String[] checkParam = { sno };
        rs = db.executeQuery(checkSql, checkParam);
        System.out.println("rs" + rs);
        if (rs.next()) {
            result = 1;
        }
        System.out.println("result" + result);
        return result;
    }
}
