package org.chimerax.demeter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Silviu-Mihnea
 * Date: 03-Mar-20
 * Time: 8:35 PM
 */

@Data
@Entity
@Table(name = "recipes", schema = "recipes")
@Accessors(chain = true)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    private int duration;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<QuantifiedIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RecipeStep> steps = new HashSet<>();

    @Column(name = "category_id")
    @JsonIgnore
    private long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", updatable = false, insertable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Category category;
}
