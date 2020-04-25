package org.chimerax.demeter.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 23-Apr-20
 * Time: 10:24 PM
 */

@Data
@Accessors(chain = true)
public class User {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String profilePicture;

    private String country;

    private String phoneNumber;
}
