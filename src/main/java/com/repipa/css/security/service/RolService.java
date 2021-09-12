package com.repipa.css.security.service;

import com.repipa.css.security.entity.Rol;
import com.repipa.css.security.enums.RolNombre;
import com.repipa.css.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rRep;

    public List<Rol> getRoles(){ return rRep.findAll();}

    public Rol saveRol(Rol r){ return rRep.save(r);}

    public String deleteRol(int id){
        rRep.deleteById(id);

        return "Eliminado rol " + id;
    }

    public Rol updateRol(Rol r){
        Rol existingRol = rRep.findById(r.getId()).orElse(null);
        existingRol.setNombre(r.getNombre());

        return rRep.save(r);
    }

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return  rRep.findByNombre(rolNombre);
    }

    public void save(Rol rol){
        rRep.save(rol);
    }
}
