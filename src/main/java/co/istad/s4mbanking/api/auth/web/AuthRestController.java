package co.istad.s4mbanking.api.auth.web;

import co.istad.s4mbanking.api.auth.AuthService;
import co.istad.s4mbanking.base.BaseApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    // 1. Register
    @PostMapping("/register")
    public BaseApi<?> register(@Valid @RequestBody RegisterDto registerDto) {

        authService.register(registerDto);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been registered successfully")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }

    // 2. Log in

}