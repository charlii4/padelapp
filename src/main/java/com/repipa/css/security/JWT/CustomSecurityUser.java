package com.repipa.css.security.JWT;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

public class CustomSecurityUser extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Map<String, Boolean> permissions;

    public CustomSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                              Map<String, Boolean> permisions) {
        super(username, password, authorities);
        this.permissions = permisions;
    }

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

}
