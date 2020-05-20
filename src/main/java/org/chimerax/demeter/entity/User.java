package org.chimerax.demeter.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 26-Apr-20
 * Time: 2:32 PM
 */
@Data
@Entity
@Table(name = "users", schema = "users")
@Accessors(chain = true)
public class User {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(unique = true, nullable = false, updatable = false)
    private String code;
}
