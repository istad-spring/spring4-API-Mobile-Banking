package co.istad.s4mbanking.api.auth;

import org.apache.ibatis.jdbc.SQL;

public class AuthProvider {

    public String buildRegisterSql() {
        return new SQL() {{
            INSERT_INTO("users");
            VALUES("email", "#{u.email}");
            VALUES("password", "#{u.password}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildCreateUserRolesSql() {
        return new SQL() {{
            INSERT_INTO("users_roles");
            VALUES("user_id", "#{userId}");
            VALUES("role_id", "#{roleId}");
        }}.toString();
    }

    public String buildSelectByEmailSql() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("email = #{email}", "is_deleted = FALSE");
        }}.toString();
    }

    public String buildSelectUserRolesSql() {
        return new SQL() {{
            SELECT("r.id, r.name");
            FROM("roles AS r");
            INNER_JOIN("users_roles AS ur ON ur.role_id = r.id");
            WHERE("ur.user_id = #{userId}");
        }}.toString();
    }

}
