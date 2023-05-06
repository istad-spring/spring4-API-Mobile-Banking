package co.istad.s4mbanking.api.user;

import co.istad.s4mbanking.api.user.web.SaveUserDto;
import co.istad.s4mbanking.api.user.web.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    User saveUserDtoToUser(SaveUserDto dto);

    UserDto userToUserDto(User user);

}
