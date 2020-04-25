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
 * Time: 8:46 PM
 */

@Data
@Entity
@Table(name = "quantified_ingredient", schema = "recipes")
@Accessors(chain = true)
public class QuantifiedIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "recipe_id")
    @JsonIgnore
    private long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Recipe recipe;

    private int quantity;

    @Column(name = "measurement_unit")
    private String unit;
}
