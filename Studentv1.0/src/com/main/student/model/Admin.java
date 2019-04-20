/**
 * 项目名：Student
 * 修改历史：
 * 作者： WJ
 * 创建时间： 2019年4月13日
 */

package com.main.student.model;

/**
 * 此处创建admin用户信息，与数据库中对应
 */
public class Admin {
    private int id;
    private String name;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
