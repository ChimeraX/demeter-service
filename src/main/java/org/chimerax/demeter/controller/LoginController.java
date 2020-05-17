package org.chimerax.demeter.controller;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.api.authorization.LoginRequest;
import org.chimerax.demeter.api.authorization.LoginResponse;
import org.chimerax.demeter.service.oauth.LoginService;
import org.chimerax.demeter.service.security.UserService;
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
    private UserService userService;

    @PostMapping
    public ResponseEntity<LoginResponse> token(@RequestBody final LoginRequest request) {
        final String c_token = loginService.login(request.getCode());
        final String d_token = userService.generate(request.getCode(), c_token);
        return ResponseEntity.ok(new LoginResponse(d_token, c_token));
    }
}
