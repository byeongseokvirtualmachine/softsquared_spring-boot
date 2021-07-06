package com.example.demo.src.servers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.servers.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.EMPTY_JWT;

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

    @ResponseBody // 이건 된거네 ? // 이건 추후에 모든 서버 탐색할 때 사용하고 싶은데
    // 어차피 로그인 한 상태라서 jwt를 들고 있을꺼고 userIdByJwt를 통해서
    @GetMapping("/servers") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetServerRes>> getServers() {
        System.out.println("ServerController jwtService : " + jwtService);
            String jwt = jwtService.getJwt(); // 대체 클라이언트가 전해주는 jwt랑, jwtService.getJwt()를 비교하는게 어떻게 가능한거지?
            System.out.println("UserController jwt : " + jwt);

        try {
            List<GetServerRes> getServerRes = serversProvider.getServers();
            return new BaseResponse<>(getServerRes);
        } catch (BaseException exception) {  
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
