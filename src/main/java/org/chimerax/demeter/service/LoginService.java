package org.chimerax.demeter.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.chimerax.demeter.api.authorization.AuthorizationRequest;
import org.chimerax.demeter.api.authorization.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 2:08 AM
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final RestTemplate restTemplate;

    @Value("${chimerax.oauth.client.clientId}")
    private String clientId;

    @Value("${chimerax.oauth.client.secret}")
    private String clientSecret;

    @Value("${chimerax.oauth.token.url}")
    private String tokenURL;

    public String login(final String code) {
        final AuthorizationRequest request = new AuthorizationRequest()
                .setClientId(clientId)
                .setSecret(clientSecret)
                .setCode(code);
        final AuthorizationResponse response =
                restTemplate.postForObject(tokenURL, request, AuthorizationResponse.class);

        if (response == null) {
            throw new RuntimeException();
        }
        return response.getToken();
    }
}
