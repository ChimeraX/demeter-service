package org.chimerax.demeter.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 23-Apr-20
 * Time: 10:55 PM
 */

@Data
@Entity
@Table(name = "reviews", schema = "recipes")
@Accessors(chain = true)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", updatable = false, insertable = false)
    private Recipe recipe;

    @Column(name = "recipe_id", updatable = false)
    private long recipeId;

    private String username;

    private boolean favorite;
}
