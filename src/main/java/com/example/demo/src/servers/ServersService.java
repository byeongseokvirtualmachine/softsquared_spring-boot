package com.example.demo.src.servers;


import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service Create, Update, Delete 의 로직 처리
@Service
public class ServersService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ServersDao serversDao;
    private final ServersProvider serversProvider;
    private final JwtService jwtService;


    @Autowired
    public ServersService(ServersDao serversDao, ServersProvider serversProvider, JwtService jwtService) {
        this.serversDao = serversDao;
        this.serversProvider = serversProvider;
        this.jwtService = jwtService;



    }

    // POST
//    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
//        //중복
//        if (userProvider.checkEmail(postUserReq.getEmail()) == 0) {
//            System.out.println("이메일이 비어있구나!");
//        }
//        else if (userProvider.checkEmail(postUserReq.getEmail()) == 1) {
//            throw new BaseException(POST_USERS_EXISTS_EMAIL);
//        }
//
//        String pwd;
//        try {
//            //암호화
//            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword());
//            System.out.println("UserService pwd : " + pwd);
//            postUserReq.setPassword(pwd);
//        } catch (Exception ignored) {
//            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
//        }
//        try {
//            int userId = userDao.createUser(postUserReq);
//            //jwt 발급.
//            String jwt = jwtService.createJwt(userId);
//            System.out.println("User Service jwt : " + jwt + "\nUser Service userId : " + userId);
//            return new PostUserRes(userId, jwt);
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public void modifyUserName(PatchUserNameReq patchUserNameReq) throws BaseException {
//        try {
//            int result = userDao.modifyUserName(patchUserNameReq);
//            System.out.println("UserService bring me here");
//            if (result == 0) {
//                throw new BaseException(MODIFY_FAIL_USERNAME);
//            }
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
}
