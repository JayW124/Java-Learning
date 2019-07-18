package service.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import model.Admin;
import service.AdminService;

/**
 * Created by wangjie on 2019/7/12
 * Descriptions:
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao dao = new AdminDaoImpl();

    @Override
    public Admin login(Admin admin) {
        return dao.login(admin.getUsername(),admin.getPassword());
    }
}
