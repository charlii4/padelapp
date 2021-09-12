package com.repipa.css.controllers;

import com.repipa.css.security.entity.Usuario;
import com.repipa.css.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService uService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/addUsuario")
    public Usuario addUsuario(@RequestBody Usuario u, BindingResult bindingResult){
/*        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos vacíos o incorrectos"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existePorNombre(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existePorEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);*/

        return uService.saveUsuario(u);
    }

    @GetMapping("/usuarios")
    public List<Usuario> findAll(){
        return uService.getUsuarios();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable int id){
        return uService.deleteUsuario(id);
    }

    @PutMapping("/updateUsuario")
    public Usuario updateUsuario(@RequestBody Usuario u){
        return uService.updateUsuario(u);
    }

}
