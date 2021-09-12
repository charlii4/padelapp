package com.repipa.css.security.service;


import com.repipa.css.security.entity.Usuario;
import com.repipa.css.security.repository.UsuarioRepository;
import com.repipa.css.security.entity.UsuarioPrincipal;
import com.repipa.css.util.EmailUserTemplate;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired
    //RolServiceImpl rolService;

    @Autowired
    UsuarioService usuarioService;


    @Autowired
    PasswordEncoder passwordEncoder;

    //@Autowired
    //AuthCodeImpl authCodeService;

/*    private static final Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);

    DateTimeFormatter agrFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByEmail(String e) {
        return usuarioRepository.findByEmail(e);
    }

    public Usuario findById(int id) {
        return usuarioRepository.findById(id);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }


    public String passwordGenerator() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+?";
        String pwd = RandomStringUtils.random(12, characters);
        return pwd;
    }


    public Usuario changePasswordFirstTime(Usuario u, String newPassword) {
        int id = u.getId();

        Usuario userToUpdate = findById(id);

        if (userToUpdate == null)
            return null;

        Date date = new Date();
        String password = newPassword;
        userToUpdate.setPassword(passwordEncoder.encode(password));
        //userToUpdate.setFirstLogging(false);
        userToUpdate.setLastUpdate(date);

        guardar(userToUpdate);
        return userToUpdate;
    }



    public Usuario ResetPassword(Usuario userToResetPassword) throws MessagingException {
        int id = userToResetPassword.getId();

        Usuario user = findById(id);

        Date date = new Date();
        user.setLastUpdate(date);
        //user.setFirstLogging(true);

        //Resetear password
        String pwd = passwordGenerator();
        user.setPassword(passwordEncoder.encode(pwd));
        guardar(user);

        //se envía el email
        EmailUserTemplate mailTemplate = new EmailUserTemplate();
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper( msg, true);

        helper.setFrom("no-reply@repipa.org");
        helper.setTo("csanchez4.91@gmail.com");
        helper.setSubject("REPIPA - Cambio de contraseña");
        helper.setText(mailTemplate.resetPassword(pwd), true);

        javaMailSender.send(msg);

        return user;
    }*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByUsuario(nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
}