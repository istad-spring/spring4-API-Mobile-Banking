package co.istad.s4mbanking.api.auth;

import co.istad.s4mbanking.api.auth.web.LogInDto;
import co.istad.s4mbanking.api.auth.web.RegisterDto;
import co.istad.s4mbanking.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final AuthMapStruct authMapStruct;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(RegisterDto registerDto) {

        User user = authMapStruct.toUser(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        authMapper.register(user);

        // After registered
        for (Integer roleId : registerDto.roleIds()) {
            authMapper.createUserRoles(user.getId(), roleId);
        }
    }

    @Override
    public void login(LogInDto logInDto) {

    }
}
