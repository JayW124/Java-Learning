/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student.dao;

import com.main.student.base.BaseDao;

import java.sql.SQLException;

/**
 * 管理员增删改查
 */
public class AdminDao extends BaseDao {
    private static AdminDao ad = null;

    public static synchronized AdminDao getInstance() {
        if (ad == null) {
            ad = new AdminDao();
        }
        return ad;
    }

    public boolean queryForLogin(String username, String password) {
        boolean result = false;
        if (username.length() == 0 || password.length() == 0) {
            return result;
        }
        String sql = "select * from admin where username=? and password=?";
        String[] param = {username, password};
        rs = db.executeQuery(sql, param);
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destroy();
        }
        return result;
    }
}
