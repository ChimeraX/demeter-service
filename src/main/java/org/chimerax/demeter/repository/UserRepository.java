package org.chimerax.demeter.repository;

import org.chimerax.common.repository.ChimeraXRepository;
import org.chimerax.demeter.entity.User;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-May-20
 * Time: 1:20 PM
 */
public interface UserRepository extends ChimeraXRepository<String, User> {

    Optional<User> findByEmail(String email);
}
