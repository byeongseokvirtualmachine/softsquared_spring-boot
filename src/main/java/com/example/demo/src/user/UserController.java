package com.example.demo.src.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /app/users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/users") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);
            }
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 회원 1명 조회 API
     * [GET] /app/:userId
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        // Get Users
        try{
            GetUserRes getUserRes = userProvider.getUser(userId);
            System.out.println("UserController getUserRes : " + getUserRes);
            return new BaseResponse<>(getUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 회원가입 API
     * [POST] /app/register
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/register")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        // 비밀번호가 없으면 안된다.
        //        info 레벨은 Console 로깅 O, 파일 로깅 X

        logger.info("INFO Level 테스트");
        if(postUserReq.getPassword() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }
        // 이메일이 없으면 안된다.
        if(postUserReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        //이메일 정규표현
        if(!isRegexEmail(postUserReq.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            System.out.println("Controller exception.printStackTrace() : ");
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }

    }
    /**
     * 로그인 API
     * [POST] /logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
        System.out.println("userController postLoginReq : " + postLoginReq);
        try{
            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
            System.out.println("userController PostLoginRes : " + postLoginRes.getUserId());
            System.out.println("userConterller PostLoginRes : " + postLoginRes.getJwt());
            System.out.println("UserController postLoginRes : " + postLoginRes);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception){
            System.out.println("userController exception.printStackTrace() : ");
            exception.printStackTrace();
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /app/:userId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{userId}")
    public BaseResponse<String> modifyUserName(@PathVariable("userId") int userId, @RequestBody User user){
        System.out.println("UserController User Value : " + user);
        System.out.println("UserController UserId : " + userId);
        try {
            //jwt에서 id 추출.
                int userIdByJwt = jwtService.getUserId();
                System.out.println("UserController userIdByJwt : " + userIdByJwt);
                //userId와 접근한 유저가 같은지 확인
                if(userId != userIdByJwt){
                    return new BaseResponse<>(INVALID_USER_JWT);
            }
            //같다면 유저네임 변경
            PatchUserNameReq patchUserNameReq = new PatchUserNameReq(userId,user.getUserName(),user.getUserTag());
            userService.modifyUserName(patchUserNameReq);

            String result = "";
        return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
