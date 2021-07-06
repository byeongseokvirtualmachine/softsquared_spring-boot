package com.example.demo.src.servers;

import com.example.demo.config.BaseException;
import com.example.demo.src.servers.model.GetServerRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

// Provider : Read의 비즈니스 로직 처리
@Service
public class ServersProvider {

    private final ServersDao serversDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ServersProvider(ServersDao serversDao, JwtService jwtService) {
        this.serversDao = serversDao;
        this.jwtService = jwtService;
    }

    public List<GetServerRes> getServers() throws BaseException {
        try {
            List<GetServerRes> getServerRes = serversDao.getServers();
            System.out.println("UserProvider getUserRes : " + getServerRes);
            return getServerRes;
        } catch (Exception exception) {
            System.out.println("Provider getUser exception.printStackTrace(): ");
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
