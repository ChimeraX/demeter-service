package org.chimerax.demeter.service.oauth;

import lombok.AllArgsConstructor;
import lombok.val;
import org.chimerax.demeter.api.UserInfoDTO;
import org.chimerax.demeter.api.authorization.AuthorizationRequest;
import org.chimerax.demeter.api.authorization.AuthorizationResponse;
import org.chimerax.demeter.api.exchange.ClientUsersRequest;
import org.chimerax.demeter.api.exchange.ClientUsersResponse;
import org.chimerax.demeter.entity.User;
import org.chimerax.demeter.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-May-20
 * Time: 12:36 AM
 */

@Service
@AllArgsConstructor
public class OAuthService {

    private RestTemplate restTemplate;
    private OAuthDetails oAuthDetails;

    private final Map<String, UserInfoDTO> cache = new HashMap<>();

    public void load(final Set<String> users){
        ClientUsersRequest request = new ClientUsersRequest()
                .setUsers(users)
                .setClientId(oAuthDetails.getClientId())
                .setSecret(oAuthDetails.getClientSecret());

        ClientUsersResponse response = restTemplate.postForObject(
                oAuthDetails.getOAuthURL() + "/users",
                request, ClientUsersResponse.class);

        assert response != null;
        cache.putAll(response.getUsers());
    }

    public UserInfoDTO getForUsername(final String username){
        return cache.get(username);
    }

    public String loginToOAuth(final String code) {
        final AuthorizationRequest request = new AuthorizationRequest()
                .setClientId(oAuthDetails.getClientId())
                .setSecret(oAuthDetails.getClientSecret())
                .setCode(code);

        final AuthorizationResponse response =
                restTemplate.postForObject(oAuthDetails.getTokenURL(), request, AuthorizationResponse.class);

        if (response == null) {
            throw new RuntimeException();
        }

        return response.getToken();
    }
}
