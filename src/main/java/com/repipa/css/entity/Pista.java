package com.repipa.css.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PISTA")
public class Pista {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private boolean luz;
    private String tipo;
}
