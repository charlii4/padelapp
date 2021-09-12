package com.repipa.css.service;

import com.repipa.css.entity.Reserva;
import com.repipa.css.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository rRep;

    public List<Reserva> getReservas() { return rRep.findAll();}

    public Reserva saveReserva(Reserva r) { return rRep.save(r);}

    public String deleteReserva(int id){
        rRep.deleteById(id);

        return "Eliminada reserva " + id;
    }

    public Reserva updateReserva(Reserva r) {
        Reserva existingReserva = rRep.findById(r.getId()).orElse(null);
        existingReserva.setId_usuario(r.getId_usuario());
        existingReserva.setId_pista(r.getId_pista());
        existingReserva.setId_tarifa(r.getId_tarifa());
        existingReserva.setFecha(r.getFecha());

        return rRep.save(r);
    }

}
