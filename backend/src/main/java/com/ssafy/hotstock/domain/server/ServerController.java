package com.ssafy.hotstock.domain.server;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/check")
    public String checkServerStatus(){
        return "check";
    }
}
