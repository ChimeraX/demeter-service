package org.chimerax.demeter.service.oauth;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.api.authorization.AuthorizationRequest;
import org.chimerax.demeter.api.authorization.AuthorizationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 2:08 AM
 */
@Service
@AllArgsConstructor
public class LoginService {

    private RestTemplate restTemplate;

    private OAuthDetails oAuthDetails;

    public String login(final String code) {
        final AuthorizationRequest request = new AuthorizationRequest()
                .setClientId(oAuthDetails.getClientId())
                .setSecret(oAuthDetails.getClientSecret())
                .setCode(code);

        System.out.println(oAuthDetails.getTokenURL());
        final AuthorizationResponse response =
                restTemplate.postForObject(oAuthDetails.getTokenURL(), request, AuthorizationResponse.class);

        if (response == null) {
            throw new RuntimeException();
        }

        return response.getToken();
    }
}
