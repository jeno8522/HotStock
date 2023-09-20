package com.ssafy.hotstock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: delete this file
@RestController
@RequiredArgsConstructor
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/check")
    public String checkServerStatus(){
        return "check";
    }
}
