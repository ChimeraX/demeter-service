package org.chimerax.demeter.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 26-Apr-20
 * Time: 3:36 PM
 */

@Data
@Accessors(chain = true)
public class UserInfoDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String profilePicture;
}
