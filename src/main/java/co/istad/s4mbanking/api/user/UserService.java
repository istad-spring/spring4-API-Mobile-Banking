package co.istad.s4mbanking.api.user;

import co.istad.s4mbanking.api.user.web.SaveUserDto;
import co.istad.s4mbanking.api.user.web.UserDto;

public interface UserService {

    UserDto create(SaveUserDto saveUserDto);

}
