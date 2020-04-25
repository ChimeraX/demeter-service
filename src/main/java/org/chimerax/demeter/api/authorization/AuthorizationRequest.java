package org.chimerax.demeter.api.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 12:04 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AuthorizationRequest {
    private String code;
    private String clientId;
    private String secret;
}
