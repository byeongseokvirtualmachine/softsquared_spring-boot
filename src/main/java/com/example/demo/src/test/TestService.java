package com.example.demo.src.test;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.test.model.*;
import com.example.demo.utils.AES128;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class TestService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TestDao testDao;
    private final TestProvider testProvider;

    @Autowired
    public TestService(TestDao testDao, TestProvider testProvider) {
        this.testDao = testDao;
        this.testProvider = testProvider;

    }
}