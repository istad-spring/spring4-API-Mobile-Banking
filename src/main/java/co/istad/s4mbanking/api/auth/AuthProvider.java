package co.istad.s4mbanking.api.auth;

import org.apache.ibatis.jdbc.SQL;

public class AuthProvider {

    public String selectByEmail() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("email = #{email}", "is_deleted = FALSE");
        }}.toString();
    }

}
