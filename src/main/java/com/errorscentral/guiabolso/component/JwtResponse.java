package com.errorscentral.guiabolso.component;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse  {

    private String jwtToken;

    public JwtResponse(String jwttoken) {
        this.jwtToken = jwttoken;
    }

    public JwtResponse() {

    }
    public String getToken() {
        return this.jwtToken;
    }
}