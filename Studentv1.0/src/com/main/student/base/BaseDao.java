/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student.base;

import com.main.student.DAO;
import com.main.student.dao.AdminDao;
import com.main.student.dao.StudentDao;
import com.main.student.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Dao基类，dao包实现对数据库的增删改查
 */
public class BaseDao {
    protected final DBUtil db = DBUtil.getDb();
    protected ResultSet rs;
    private static BaseDao baseDao;

    public BaseDao() {
        init();
    }

    public void init() {}

    public static synchronized BaseDao getAbilityDao(DAO dao) {
        switch (dao) {
            case AdminDAO:
                if (baseDao == null || baseDao.getClass() != AdminDao.class) {
                    baseDao = AdminDao.getInstance();
                }
                break;
            case StudentDAO:
                if (baseDao == null || baseDao.getClass() != StudentDao.class) {
                    baseDao = StudentDao.getInstance();
                }
                break;
            default:
                break;
        }
        return baseDao;
    }

    protected void destroy() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


