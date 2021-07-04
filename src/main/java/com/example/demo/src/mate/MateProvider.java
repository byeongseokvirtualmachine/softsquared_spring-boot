package com.example.demo.src.mate;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.mate.MateDao;
import com.example.demo.src.mate.model.*;
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
public class MateProvider {

    private final MateDao mateDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MateProvider(MateDao mateDao, JwtService jwtService) {
        this.mateDao = mateDao;
        this.jwtService = jwtService;
    }

    public List<GetMateRes> getMates() throws BaseException {
        try {
            List<GetMateRes> getMateRes = mateDao.getMates();
            return getMateRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetMateRes getMate(int userId) throws BaseException {
        System.out.println("Provider getMate 시작 : ");
        try {
            GetMateRes getMateRes = mateDao.getMate(userId);
            return getMateRes;
        } catch (Exception exception) {
            System.out.println("mateDao exception : ");
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkMate(int userId,int mateId) throws BaseException {
        System.out.println("친구 확인 중... userId : ");
        System.out.println(userId);
        try {
            return mateDao.checkMateId(userId, mateId);

        } catch (Exception exception) {
            System.out.println("친구가 있는지 없는지 확인이 안 됨");
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
