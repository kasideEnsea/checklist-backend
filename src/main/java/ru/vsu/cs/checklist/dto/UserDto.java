package ru.vsu.cs.checklist.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String role;
    private String about;
    private String login;
}
