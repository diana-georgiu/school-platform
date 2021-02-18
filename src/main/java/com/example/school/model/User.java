package com.example.school.model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
        @Id
        public Integer id;

        @Column
        public String username;

        @Column
        public String pwd;

        @Column
        public Integer enabled;

        @Column
        public Integer role;


    public User() {
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
