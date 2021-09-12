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
@Table(name = "RESERVA")
public class Reserva {
    @Id
    @GeneratedValue
    private int id;
    private int id_usuario;
    private int id_pista;
    private int id_tarifa;
    private String fecha;
}
