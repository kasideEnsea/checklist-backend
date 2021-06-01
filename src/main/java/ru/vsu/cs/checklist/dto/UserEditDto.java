package ru.vsu.cs.checklist.dto;

import lombok.Data;

@Data
public class UserEditDto {
    private Integer id;
    private String login;
    private String name;
    private String about;
    private String oldPassword;
    private String newPassword;
}
