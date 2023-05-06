package co.istad.s4mbanking.api.user.web;

import co.istad.s4mbanking.api.user.UserService;
import co.istad.s4mbanking.api.user.web.SaveUserDto;
import co.istad.s4mbanking.base.BaseApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public BaseApi<?> create(@Valid @RequestBody SaveUserDto saveUserDto) {
        UserDto userDto = userService.create(saveUserDto);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been saved")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

}
