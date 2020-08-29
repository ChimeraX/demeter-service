package org.chimerax.demeter.repository;

import org.chimerax.demeter.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 29-Aug-20
 * Time: 7:01 PM
 */

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource({"classpath:application-test.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTesting {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new User().setCode("code").setEmail("email"));
    }

    @Test
    void findByEmail() {
        assertTrue(userRepository.findByEmail("email").isPresent());
    }
}
