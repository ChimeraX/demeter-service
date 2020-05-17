package org.chimerax.demeter.configuration;

import org.chimerax.demeter.service.oauth.OAuthDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 27-Apr-20
 * Time: 2:01 AM
 */

@Configuration
public class OAuthConfiguration {

    @Value("${chimerax.oauth.client.clientId}")
    private String clientId;

    @Value("${chimerax.oauth.client.secret}")
    private String clientSecret;

    @Value("${chimerax.oauth.server.url}")
    private String serverURL;

    @Bean
    public OAuthDetails oAuthDetails() {
        return new OAuthDetails() {
            @Override
            public String getClientId() {
                return clientId;
            }

            @Override
            public String getClientSecret() {
                return clientSecret;
            }

            @Override
            public String getOAuthURL() {
                return serverURL;
            }
        };
    }
}
