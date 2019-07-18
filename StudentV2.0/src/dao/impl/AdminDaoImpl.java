package dao.impl;

import dao.AdminDao;
import model.Admin;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

/**
 * Created by wangjie on 2019/7/12
 * Descriptions:
 */
public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Admin login(String username, String password) {
        try {
            String sql = "select * from admin where username = ? and password = ?";
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), username, password);
            return admin;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
