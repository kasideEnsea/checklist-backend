package ru.vsu.cs.checklist.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.service.RoleService;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Keychain {
    public static final String ROLE_USER = "User";
    public static final String ROLE_ADMIN = "Admin";
    static final String JWT_KEY = System.getenv("JWT_KEY");
    static final Set<String> admins = getEnvAdmins();
    private final RoleService roleService;

    private static Set<String> getEnvAdmins() {
        String str = System.getenv("ADMINS");
        if (str == null) {
            return Collections.emptySet();
        }
        String[] logins = str.split(" ,;");
        return Arrays.stream(logins)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    public Set<GrantedAuthority> getAuthorities(String login, int userId) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        authorities.add(() -> ROLE_USER);
        if (admins.contains(login)) {
            authorities.add(() -> ROLE_ADMIN);
        }
        for (String role : roleService.getAllRoles(userId)) {
            authorities.add(() -> role);
        }
        return authorities;
    }

    public List<String> getAuthoritiesStrings(String login, int userId) {
        return getAuthorities(login, userId).stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
