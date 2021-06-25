package com.example.demo.src.channel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.channel.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app")
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
}
