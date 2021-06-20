package com.example.demo.src.test;

import com.example.demo.src.test.model.GetTestRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.test.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app/test")
public class TestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TestProvider testProvider;
    @Autowired
    private final TestService testService;


    public TestController(TestProvider testProvider, TestService testService) {
        this.testProvider = testProvider;
        this.testService = testService;
    }


    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/test1/:userId
    public BaseResponse<GetTestRes> getTest(@PathVariable("userId") int userId) {
        System.out.println("userId 확인 : " + userId);
        System.out.println("BaseResponse<>(getTestRes) : " + getTest(userId));
        // Get Users
        try {
            GetTestRes getTestRes = testProvider.getTest(userId);
            System.out.println("getTestRes : " + getTestRes);
            return new BaseResponse<>(getTestRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }
    @ResponseBody
    @GetMapping("/log")
    public String getAll() {
        System.out.println("테스트");
//        trace, debug 레벨은 Console X, 파일 로깅 X
//        logger.trace("TRACE Level 테스트");
//        logger.debug("DEBUG Level 테스트");

//        info 레벨은 Console 로깅 O, 파일 로깅 X
        logger.info("INFO Level 테스트");
//        warn 레벨은 Console 로깅 O, 파일 로깅 O
        logger.warn("Warn Level 테스트");
//        error 레벨은 Console 로깅 O, 파일 로깅 O (app.log 뿐만 아니라 error.log 에도 로깅 됨)
//        app.log 와 error.log 는 날짜가 바뀌면 자동으로 *.gz 으로 압축 백업됨
        logger.error("ERROR Level 테스트");

        return "Success Test";
    }

}
