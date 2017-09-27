package com.harambase.pioneer.security.pojo;

import org.apache.shiro.authc.AuthenticationToken;

public class ShiroToken implements AuthenticationToken {
    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
