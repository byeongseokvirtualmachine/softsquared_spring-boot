package com.example.demo.src.test;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.test.model.*;
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
public class TestProvider {
    private final TestDao testDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TestProvider(TestDao testDao) {
        this.testDao = testDao;
    }

    public List<GetTestRes> getTest() throws BaseException {
        try {
            List<GetTestRes> getTestRes = testDao.getTest();
            return getTestRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetTestRes getTest(int userId) throws BaseException {
        try {
            GetTestRes getTestRes = testDao.getTest(userId);
            return getTestRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
