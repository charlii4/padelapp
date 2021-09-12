package com.repipa.css.controllers;

import com.repipa.css.security.entity.Rol;
import com.repipa.css.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rService;

    @PostMapping("/addRol")
    public Rol addRol(@RequestBody Rol r) { return rService.saveRol(r);}

    @GetMapping("/rol")
    public List<Rol> findAll() { return rService.getRoles();}

    @DeleteMapping("/delete/{id}")
    public String deleteRol(@PathVariable int id) { return rService.deleteRol(id);}

    @PutMapping("/updateRol")
    public Rol updateRol(@RequestBody Rol r) { return rService.updateRol(r);}
}
