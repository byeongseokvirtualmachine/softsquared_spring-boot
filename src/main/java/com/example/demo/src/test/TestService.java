package com.example.demo.src.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TestProvider testProvider;

    @Autowired
    public TestService(TestDao testDao, TestProvider testProvider) {
        this.testProvider = testProvider;

    }
}