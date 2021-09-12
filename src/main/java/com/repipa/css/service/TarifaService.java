package com.repipa.css.service;

import com.repipa.css.entity.Tarifa;
import com.repipa.css.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tRep;

    public List<Tarifa> getTarifas() { return tRep.findAll();}

    public Tarifa saveTarifa(Tarifa t) {
        return tRep.save(t);
    }

    public String deleteTarifa(int id){
        tRep.deleteById(id);

        return "Tarifa eliminada " + id;
    }

    public Tarifa updateTarifa(Tarifa t){
        Tarifa existingTarifa = tRep.findById(t.getId()).orElse(null);
        existingTarifa.setHora_inicio(t.getHora_inicio());
        existingTarifa.setHora_fin(t.getHora_fin());
        existingTarifa.setPrecio(t.getPrecio());

        return tRep.save(t);
    }

}
