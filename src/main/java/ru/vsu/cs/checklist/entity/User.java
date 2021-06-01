package ru.vsu.cs.checklist.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checklist_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    private String role;
    @Column(name = "about")
    private String about;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "pass_token")
    private String passToken;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "salt")
    private String salt;

    public User() {

    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User(String login, String password, String salt, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.salt = salt;
    }

}
