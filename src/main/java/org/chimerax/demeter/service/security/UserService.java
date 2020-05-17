package org.chimerax.demeter.service.security;


/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-May-20
 * Time: 1:19 PM
 */

public interface UserService {

    String generate(final String code, final String c_token);
}
