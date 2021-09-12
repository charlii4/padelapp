package com.repipa.css.controllers;

import com.repipa.css.entity.Reserva;
import com.repipa.css.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService rService;

    @PostMapping("/addReserva")
    public Reserva addReserva(@RequestBody Reserva r) {
        return rService.saveReserva(r);
    }

    @GetMapping("/reservas")
    public List<Reserva> findAll() {return rService.getReservas();}

    @DeleteMapping("/delete/{id}")
    public String deleteReserva(@PathVariable int id) {return rService.deleteReserva(id);}

    @PutMapping("/updateReserva")
    public Reserva updateReserva(@RequestBody Reserva r) { return rService.updateReserva(r);}
}
