package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    public List<GetUserRes> getUsers() throws BaseException {
        try {
            List<GetUserRes> getUserRes = userDao.getUsers();
            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsersByEmail(String email) throws BaseException {
        try {
            List<GetUserRes> getUsersRes = userDao.getUsersByEmail(email);
            return getUsersRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public GetUserRes getUser(int userId) throws BaseException {
        System.out.println("UserProvider userId : " + userId);
        try {
            GetUserRes getUserRes = userDao.getUser(userId);
            System.out.println("UserProvider getUserRes : " + getUserRes);
            return getUserRes;
        } catch (Exception exception) {
            System.out.println("Provider exception.printStackTrace(): " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException {
        try {
            return userDao.checkEmail(email);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {

        User user = userDao.getPwd(postLoginReq);
        System.out.println("UserProvider user : " + user);
        String password;
        System.out.println("UserProvider user : " + user);
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPassword());
            System.out.println("UserProvider password : " + password);
        } catch (Exception ignored) {
            System.out.println("UserProvider ignored : ");
            ignored.printStackTrace();
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if (postLoginReq.getPassword().equals(password)) {
            int userId = userDao.getPwd(postLoginReq).getUserId();
            String jwt = jwtService.createJwt(userId);
            System.out.println("UserProvider userId : " + userId);
            System.out.println("UserProvider jwt : " + jwt);
            return new PostLoginRes(userId, jwt);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

}
