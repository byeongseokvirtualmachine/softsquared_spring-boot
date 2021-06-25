package com.example.demo.src.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers() {
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("status"),
                        rs.getString("userName"),
                        rs.getString("userTag"),
                        rs.getString("flagOnOff"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"))
        );
    }

    public List<GetUserRes> getUsersByEmail(String email) {
        System.out.println("getUsersByEmail 접근");
        String getUsersByEmailQuery = "select userId, userName, userTag, flagOnOff, profileImageUrl, phone, email, password from User where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("status"),
                        rs.getString("userName"),
                        rs.getString("userTag"),
                        rs.getString("flagOnOff"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"))
        );

    }

    public GetUserRes getUser(int userId) {
        System.out.println("getUser 접근");
        String getUserQuery = "select * from User where userId = ?";
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("status"),
                        rs.getString("userName"),
                        rs.getString("userTag"),
                        rs.getString("flagOnOff"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password")),
                getUserParams);
    }


    public int createUser(PostUserReq postUserReq) {
        System.out.println("createUser 접근 ");
        String createUserQuery = "insert into User (userId, userName, userTag, email, password) VALUES (?, ?, ?, ?, ?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserId(), postUserReq.getUserName(), postUserReq.getUserTag(), postUserReq.getEmail(), postUserReq.getPassword()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertUserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertUserIdQuery, int.class);
    }

    public int checkEmail(String email) {
        System.out.println("checkEmail 접근");
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

//    public int checkUserTag(String userTag){
//        String checkUserTagQuery = "select ";
//
//        return this.jdbcTemplate.queryForObject(checkUserTagQuery, int.class, checkUserTagParams)
//    }

    public int modifyUserName(PatchUserNameReq patchUserNameReq) {
        System.out.println("modifyUserName 접근");
        String modifyUserNameQuery = "update User set userName = ? where userId = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserNameReq.getUserName(), patchUserNameReq.getUserId()};
        return this.jdbcTemplate.update(modifyUserNameQuery, modifyUserNameParams);
    }

    /*
    new Object[]{postUserReq.getUserId(), postUserReq.getUserName(),postUserReq.getUserTag(), postUserReq.getEmail(), postUserReq.getPassword()};
     */
    public User getPwd(PostLoginReq postLoginReq) {
        System.out.println("getPwd 접근");
        String getPwdQuery = "SELECT userId, status, userName, userTag, flagOnOff, profileImageUrl, phone, email, password, nitro FROM User WHERE email = ?";
        String getPwdParams = postLoginReq.getEmail();
        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("userId"),
                        rs.getString("status"),
                        rs.getString("userName"),
                        rs.getString("userTag"),
                        rs.getString("flagOnOff"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("nitro")
                ),
                getPwdParams);

    }


}
