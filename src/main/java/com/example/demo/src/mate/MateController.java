package com.example.demo.src.mate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.mate.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels/mate")
public class MateController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MateProvider mateProvider;
    @Autowired
    private final MateService mateService;
    @Autowired
    private final JwtService jwtService;


    public MateController(MateProvider mateProvider, MateService mateService, JwtService jwtService) {
        this.mateProvider = mateProvider;
        this.mateService = mateService;
        this.jwtService = jwtService;
    }

    /**
     * 친구 전체 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     *
     * @return BaseResponse<List < GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetMateRes>> getMates() {
        try {
            List<GetMateRes> getMateRes = mateProvider.getMates();
            return new BaseResponse<>(getMateRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 특정 친구 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     *
     * @return BaseResponse<List < GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<GetMateRes> getMate(@RequestParam("userId") int userId) {
        // Get Mate
        System.out.println("getMate 시작");
        try {
            GetMateRes getMateRes = mateProvider.getMate(userId);
            System.out.println("getMateRes 결과값 : ");
            return new BaseResponse<>(getMateRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ResponseBody
    @PostMapping("/add")
    public BaseResponse<PostMateRes> createMate(@RequestBody PostMateReq postMateReq) {
        try {
              // TODO : 형식적 validation처리 해야함.
            System.out.println("먼저 친구 만들기 절차 들어감");
            PostMateRes postMateRes = mateService.createMate(postMateReq);
            return new BaseResponse<>(postMateRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }
}

