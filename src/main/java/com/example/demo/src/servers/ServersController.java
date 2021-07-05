package com.example.demo.src.servers;


import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.servers.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

// 사실 이게 채널이 나와야하는건데 일단 모든 서버가 나오는 걸로 만들자.
@RestController
@RequestMapping("/channels") // (GET) 127.0.0.1:9000/channals/
public class ServersController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ServersProvider serversProvider;
    @Autowired
    private final ServersService serversService;
    @Autowired
    private final JwtService jwtService;


    public ServersController(ServersProvider serversProvider, ServersService serversService, JwtService jwtService) {
        this.serversProvider = serversProvider;
        this.serversService = serversService;
        this.jwtService = jwtService;
    }

//    @ResponseBody // 이건 된거네 ?
//    @GetMapping("/servers") // (GET) 127.0.0.1:9000/app/users
//    public BaseResponse<List<GetServerRes>> getServers() {
//
//        try {
//            String accessToken = jwtService.getJwt(); // jwt 를 입력해 주세요.
//            if ( ) {
//
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            }
//        try {
//            List<GetServerRes> getServerRes = serversProvider.getServers();
//            return new BaseResponse<>(getServerRes);
//        } catch (BaseException exception) {
//
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
}
