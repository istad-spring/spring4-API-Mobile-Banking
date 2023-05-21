package co.istad.s4mbanking.api.auth;

import co.istad.s4mbanking.api.auth.web.LogInDto;
import co.istad.s4mbanking.api.auth.web.RegisterDto;

public interface AuthService {

    void register(RegisterDto registerDto);

    void login(LogInDto logInDto);

}
