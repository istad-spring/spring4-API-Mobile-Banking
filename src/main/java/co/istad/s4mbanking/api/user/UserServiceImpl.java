package co.istad.s4mbanking.api.user;

import co.istad.s4mbanking.api.user.web.SaveUserDto;
import co.istad.s4mbanking.api.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto create(SaveUserDto saveUserDto) {
        User user = userMapStruct.saveUserDtoToUser(saveUserDto);
        userMapper.insert(user);
        user = userMapper.selectById(user.getId()).orElseThrow(() ->
                        new RuntimeException("User is not found"));
        return userMapStruct.userToUserDto(user);
    }

}
