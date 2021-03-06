package ru.vsu.cs.checklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.checklist.dto.AuthorizedUserDto;
import ru.vsu.cs.checklist.dto.LoginOptionsDto;
import ru.vsu.cs.checklist.service.AuthorizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/")
    public AuthorizedUserDto authUser(@Valid @RequestBody LoginOptionsDto loginOptionsDto) {
        return authorizationService.authorizeUser(loginOptionsDto.getLogin(), loginOptionsDto.getPassword());
    }
}
