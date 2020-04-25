package org.chimerax.demeter.controller;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.api.authorization.LoginRequest;
import org.chimerax.demeter.api.authorization.LoginResponse;
import org.chimerax.demeter.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 2:00 AM
 */


@RestController
@RequestMapping("/authenticate")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> token(@RequestBody final LoginRequest request) {
        return ResponseEntity.ok(new LoginResponse(loginService.login(request.getCode())));
    }
}
