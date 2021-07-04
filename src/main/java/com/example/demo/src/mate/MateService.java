package com.example.demo.src.mate;


import com.example.demo.config.BaseException;
import com.example.demo.src.mate.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class MateService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MateDao mateDao;
    private final MateProvider mateProvider;
    private final JwtService jwtService;

    @Autowired
    public MateService(MateDao mateDao, MateProvider mateProvider, JwtService jwtService) {
        this.mateDao = mateDao;
        this.mateProvider = mateProvider;
        this.jwtService = jwtService;

    }
    // 근데 jwt를 어느 도메인까지 범벅을 해야하지?

    public PostMateRes createMate(PostMateReq postMateReq) throws BaseException{
        System.out.println("중복되는 친구 확인 중");
        if(postMateReq.getMateId() == mateProvider.checkMate(postMateReq.getUserId(), postMateReq.getMateId())){
            throw new BaseException(POST_MATE_EXISTS_ALREADY);
        }
        int MateListId, userId, mateId;
        String status;
        try {
            System.out.println("여기까지 도달하는지?");
            MateListId = mateDao.createMate(postMateReq);
            userId = postMateReq.getUserId();
            mateId = postMateReq.getMateId();
            status = postMateReq.getStatus();
            return new PostMateRes(MateListId, userId, mateId, status);
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }


    }
}