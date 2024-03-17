package com.example.userservice;

import com.example.userservice.SecurityPackage.Repository.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    private JpaRegisteredClientRepository  jpaRegisteredClientRepository;

    @Autowired
    public UserServiceApplicationTests(JpaRegisteredClientRepository jpaRegisteredClientRepository){
        this.jpaRegisteredClientRepository = jpaRegisteredClientRepository;
    }

    @Test
    void createPostManRegisteredClient() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("$2a$12$MNa6HfSxLuq4CriV5vfYme6DBLR5zWDGIbGyeZ5nK9CnxItfLBZaO")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                //after login it instruct the system go to this site
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                .scope(OidcScopes.OPENID)
                //defines the scope of the role
                .scope(OidcScopes.PROFILE)
                .scope("ADMIN")
                .scope("STUDENT")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        jpaRegisteredClientRepository.save(oidcClient);

    }

}
