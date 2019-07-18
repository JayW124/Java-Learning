package dao;

import model.Admin;

/**
 * Created by wangjie on 2019/7/12
 * Descriptions:
 */
public interface AdminDao {
    public Admin login(String username, String password);
}
