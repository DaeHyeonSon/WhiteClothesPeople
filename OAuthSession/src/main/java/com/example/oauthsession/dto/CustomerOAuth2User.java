package com.example.oauthsession.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomerOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;
    private final String role;

    public CustomerOAuth2User(OAuth2Response oAuth2Response, String role) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }


    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Response.getName();
    }

    public String getUsername() {

        return oAuth2Response.getProvider() +" "+ oAuth2Response.getProviderId();
    }
}
