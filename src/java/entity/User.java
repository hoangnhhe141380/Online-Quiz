package entity;

import java.sql.Date;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private int roleId;
    private List<Quiz> quizs;
    private String email;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Quiz> getQuizs() {
        return quizs;
    }

    public void setQuizs(List<Quiz> quizs) {
        this.quizs = quizs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User(int id, String username, String password, int roleId, List<Quiz> quizs, String email, Date createdDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.quizs = quizs;
        this.email = email;
        this.createdDate = createdDate;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", roleId=" + roleId + ", quizs=" + quizs + ", email=" + email + '}';
    }

}
