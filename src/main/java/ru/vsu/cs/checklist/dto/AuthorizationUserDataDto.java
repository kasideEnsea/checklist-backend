package ru.vsu.cs.checklist.dto;

import lombok.Data;

@Data
public class AuthorizationUserDataDto {
    private String login;
    private String password;
}
