package com.repipa.css.controllers;

import com.repipa.css.entity.Tarifa;
import com.repipa.css.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    @Autowired
    private TarifaService tService;

    @PostMapping("/addTarifa")
    public Tarifa addTarifa(@RequestBody Tarifa t) {return tService.saveTarifa(t);}

    @GetMapping("/tarifas")
    public List<Tarifa> findAll() {
        return tService.getTarifas();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTarifa(@PathVariable int id){ return tService.deleteTarifa(id);}

    @PutMapping("/updateTarifa")
    public Tarifa updateTarifa(@RequestBody Tarifa t) {return tService.updateTarifa(t);}
}
