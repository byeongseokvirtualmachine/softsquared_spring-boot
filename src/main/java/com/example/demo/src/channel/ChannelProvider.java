package com.example.demo.src.channel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.channel.model.Channel;
import com.example.demo.src.channel.ChannelDao;
import com.example.demo.src.channel.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ChannelProvider {

    private final ChannelDao channelDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ChannelProvider(ChannelDao channelDao, JwtService jwtService) {
        this.channelDao = channelDao;
        this.jwtService = jwtService;
    }

    public List<GetChannelRes> getChannel() throws BaseException {
        try {
            List<GetChannelRes> getChannelRes = channelDao.getChannels();
            return getChannelRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
