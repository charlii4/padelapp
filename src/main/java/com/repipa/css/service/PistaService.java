package com.repipa.css.service;

import com.repipa.css.entity.Pista;
import com.repipa.css.repository.PistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PistaService {

    @Autowired
    private PistaRepository pRep;

    public List<Pista> getPistas() { return pRep.findAll();}

    public Pista savePista(Pista p) { return pRep.save(p);}

    public String deletePista(int id) {
        pRep.deleteById(id);

        return "Pista eliminada " + id;
    }

    public Pista updatePista(Pista p){
        Pista existingPista = pRep.findById(p.getId()).orElse(null);
        existingPista.setNombre(p.getNombre());
        existingPista.setLuz(p.isLuz());
        existingPista.setTipo(p.getTipo());

        return  pRep.save(p);
    }
}
