package com.errorscentral.guiabolso.controller;

import com.errorscentral.guiabolso.component.JwtRequest;
import com.errorscentral.guiabolso.component.JwtResponse;
import com.errorscentral.guiabolso.entity.User;
import com.errorscentral.guiabolso.component.JwtTokenUtil;
import com.errorscentral.guiabolso.service.UserService;
import com.errorscentral.guiabolso.component.DecodeBase64;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private DecodeBase64 decodeBase64;

    @Autowired
    private UserService userService;

    @PostMapping(path = "login", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Authenticated user.")})
    public ResponseEntity login(@RequestBody JwtRequest request) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), decodeBase64.decode(request.getPassword())));
         return ResponseEntity.ok(new JwtResponse(jwtTokenUtil.generateToken(request.getEmail())));
    }

    @PostMapping(path = "register", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully registered.")})
    public ResponseEntity saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
