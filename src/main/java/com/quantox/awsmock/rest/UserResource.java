package com.quantox.awsmock.rest;

import com.quantox.awsmock.service.MachineService;
import com.quantox.awsmock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;




}
