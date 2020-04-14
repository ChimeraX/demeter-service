package org.chimerax.demeter;

import lombok.experimental.Accessors;
import org.chimerax.demeter.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
class DemeterApplicationTests {

    @Test
    void contextLoads() {
    }

}
