package org.chimerax.demeter.service.oauth;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.api.exchange.ExchangeRequest;
import org.chimerax.demeter.api.exchange.ExchangeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 27-Apr-20
 * Time: 2:00 AM
 */

@Service
@AllArgsConstructor
public class CodeService {

    private RestTemplate restTemplate;
    private OAuthDetails oAuthDetails;

    public String exchangeCodes(final List<String> codes) {
        final ExchangeRequest request = new ExchangeRequest()
                .setClientId(oAuthDetails.getClientId())
                .setSecret(oAuthDetails.getClientSecret())
                .setCodes(codes);

        final ExchangeResponse response = restTemplate.postForObject(
                oAuthDetails.getExchangeURL(),
                request,
                ExchangeResponse.class);

        if (response == null) {
            throw new RuntimeException();
        }

        return response.getCode();
    }

}
