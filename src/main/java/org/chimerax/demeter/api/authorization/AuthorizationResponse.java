package org.chimerax.demeter.api.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 12:04 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponse {
    private String token;
}
