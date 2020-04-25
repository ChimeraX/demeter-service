package org.chimerax.demeter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Author: Silviu-Mihnea
 * Date: 03-Mar-20
 * Time: 9:26 PM
 */

@Data
@Entity
@Table(name = "recipe_step", schema = "recipes")
@Accessors(chain = true)
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step_order")
    private int order;

    @Column(length = 1000)
    private String description;

    @Column(name = "recipe_id")
    @JsonIgnore
    private long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", updatable = false, insertable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Recipe recipe;
}
