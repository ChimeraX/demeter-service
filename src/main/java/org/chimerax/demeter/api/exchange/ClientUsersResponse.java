package org.chimerax.demeter.api.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.chimerax.demeter.api.UserInfoDTO;

import java.util.Map;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 12:04 PM
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUsersResponse {

    private Map<String, UserInfoDTO> users;
}
