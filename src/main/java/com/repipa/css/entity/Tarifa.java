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
@Table(name = "TARIFA")
public class Tarifa {
    @Id
    @GeneratedValue
    private int id;
    private String hora_inicio;
    private String hora_fin;
    private String precio;
}
