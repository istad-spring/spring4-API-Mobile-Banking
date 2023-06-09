package co.istad.s4mbanking.api.auth;

import co.istad.s4mbanking.api.user.Role;
import co.istad.s4mbanking.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {

    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void register(@Param("u") User user);

    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRolesSql")
    void createUserRoles(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailSql")
    @Results(id = "authResultMap", value = {
            @Result(property = "roles", column = "id",
                    many = @Many(select = "selectUserRoles")),
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "isStudent", column = "is_student"),
            @Result(property = "studentCardId", column = "student_card_id")
    })
    Optional<User> selectByEmail(String email);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectUserRolesSql")
    List<Role> selectUserRoles(Integer userId);

}
