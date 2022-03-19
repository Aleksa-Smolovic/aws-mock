package com.quantox.awsmock.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestResource {

    @GetMapping("/test")
    public void test(){
        System.out.println("Ulazi");
    }

}
