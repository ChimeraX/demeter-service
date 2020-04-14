package org.chimerax.demeter.repository;

import org.chimerax.demeter.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 09-Apr-20
 * Time: 11:54 PM
 */

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource({"classpath:application-test.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category parent = new Category().setName("Parent").setImage("IMAGE");

    @BeforeEach
    public void arrange() {
        categoryRepository.save(parent);
        for (int i = 0; i < 5; i++) {
            Category category = new Category().setName("Child" + i).setImage("").setParentId(parent.getId());
            categoryRepository.save(category);
        }
    }

    @Test
    public void findAllWhenParentIdNotSet() {
        // act
        Page<Category> page = categoryRepository.findAll(CategorySpecifications.isParentCategory(),
                PageRequest.of(0, 20));

        // assert
        assertEquals(page.getTotalElements(), 1);
        assertTrue(page.getContent().contains(parent));
    }

    @Test
    public void findAllWhenParentIdSet() {
        // act
        Page<Category> page = categoryRepository.findAll(CategorySpecifications.byParentCategory(parent.getId()),
                PageRequest.of(0, 20));

        // assert
        assertEquals(page.getTotalElements(), 5);
        assertFalse(page.getContent().contains(parent));
    }

}
