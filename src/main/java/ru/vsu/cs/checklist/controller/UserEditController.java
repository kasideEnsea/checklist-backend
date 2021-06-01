package ru.vsu.cs.checklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.checklist.dto.UserEditDto;
import ru.vsu.cs.checklist.service.ProfileEditService;

@RestController
@RequestMapping("/edit")
@RequiredArgsConstructor
public class UserEditController {
    private final ProfileEditService userService;

    @PostMapping("/")
    public void editUser(@RequestBody UserEditDto dto) {
        userService.edit(dto);
    }
}
