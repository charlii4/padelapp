package com.repipa.css.controllers;

import com.repipa.css.entity.Pista;
import com.repipa.css.service.PistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pista")
public class PistaController {

    @Autowired
    private PistaService pService;

    @PostMapping("/addPista")
    public Pista addPista(@RequestBody Pista p) {return pService.savePista(p);}

    @GetMapping("/pistas")
    public List<Pista> findAll() { return pService.getPistas();}

    @DeleteMapping("/delete/{id}")
    public String deletePista(@PathVariable int id) { return pService.deletePista(id);}

    @PutMapping("/updatePista")
    public Pista updatePista(@RequestBody Pista p) {
        return pService.updatePista(p);
    }
}
