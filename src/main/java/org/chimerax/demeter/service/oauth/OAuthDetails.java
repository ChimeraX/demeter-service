package org.chimerax.demeter.service.oauth;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 27-Apr-20
 * Time: 2:02 AM
 */
public interface OAuthDetails {

    String getClientId();

    String getClientSecret();

    String getOAuthURL();

    default String getUserInfoURL() {
        return getOAuthURL() + "/userinfo";
    }

    default String getTokenURL() {
        return getOAuthURL() + "/token";
    }

    default String getExchangeURL() {
        return getOAuthURL() + "/exchange";
    }
}
