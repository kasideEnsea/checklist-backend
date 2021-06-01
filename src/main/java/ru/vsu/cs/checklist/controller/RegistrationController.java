package ru.vsu.cs.checklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.checklist.dto.AuthorizedUserDto;
import ru.vsu.cs.checklist.dto.RegistrationUserDataDto;
import ru.vsu.cs.checklist.service.AuthorizationService;
import ru.vsu.cs.checklist.service.CurrentUserLogService;
import ru.vsu.cs.checklist.service.RegistrationService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final AuthorizationService authorizationService;
    private final CurrentUserLogService logService;

    @PostMapping("/")
    public AuthorizedUserDto register(@RequestBody RegistrationUserDataDto dto) {
        registrationService.register(dto.getLogin(), dto.getPassword(), dto.getName());
        AuthorizedUserDto authorizedUserDto = authorizationService.authorizeUser(dto.getLogin(), dto.getPassword());
        logService.logRegistered(authorizedUserDto.getUserDto().getId());
        return authorizedUserDto;
    }
}
