package org.chimerax.demeter.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Author: Silviu-Mihnea
 * Date: 03-Mar-20
 * Time: 8:36 PM
 */

@Data
@Entity
@Table(name = "ingredients", schema = "recipes")
@Accessors(chain = true)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
