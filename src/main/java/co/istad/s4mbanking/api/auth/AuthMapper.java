package co.istad.s4mbanking.api.auth;

import co.istad.s4mbanking.api.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {

    @SelectProvider(type = AuthProvider.class)
    Optional<User> selectByEmail(String email);

}
