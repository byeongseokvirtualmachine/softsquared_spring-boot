package com.example.demo.src.test;

import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.test.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/test1")
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
        // Get Users
        try {
            GetTestRes getTestRes = testProvider.getTest(userId);
            return new BaseResponse<>(getTestRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
