package com.example.demo.src.channel;


import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.channel.model.*;
import com.example.demo.src.servers.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private final ChannelProvider channelProvider;
    @Autowired
    private final ChannelService channelService;
    @Autowired
    private final JwtService jwtService;


    public ChannelController(ChannelProvider channelProvider, ChannelService channelService, JwtService jwtService) {
        this.channelProvider = channelProvider;
        this.channelService = channelService;
        this.jwtService = jwtService;

    }

    /*
     * 특정 서버 내 채널 조회 API
     *
     * [GET] /channels/@me
     * 서버의 정보를 갖고 오고 싶은거지
     *
     * @return BaseRespose<List <GetChannelRes>>
     */
    // path variable

//    @ResponseBody
//    @GetMapping("/serverId") // (GET) 127.0.0.1:9000/Channes/:serverId
//    public BaseResponse<List<GetUserRes>> getServers(@RequestParam int serverId) {
//        // Get Server
//        try {
//            GetServerRes getServerRes = serversProvider.getServer();
//
//            return new BaseResponse<>(getServerRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getstatus()));
//        }
//
//    }



}
