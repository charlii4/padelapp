package com.repipa.css.security.controller;

import com.repipa.css.security.JWT.JWTConfigurer;
import com.repipa.css.security.entity.Rol;
import com.repipa.css.security.entity.Usuario;
import com.repipa.css.security.enums.RolNombre;
import com.repipa.css.security.service.UsuarioService;
import com.repipa.css.security.repository.UsuarioRepository;
import com.repipa.css.security.DTO.JwtDTO;
import com.repipa.css.security.DTO.LoginUsuario;
import com.repipa.css.security.DTO.Mensaje;
import com.repipa.css.security.JWT.JwtProvider;
import com.repipa.css.security.service.RolService;
import com.repipa.css.util.EmailUserTemplate;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.mime.MimeMessage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   // private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioRepository usuarioRepository;


    //@Autowired
    //private AuthCodeImpl authCodeService;

    DateTimeFormatter agrFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Validated @RequestBody Usuario nuevoUsuario) {
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya está registrado"), HttpStatus.BAD_REQUEST);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+?";
        String pwd = RandomStringUtils.random(10, characters);


        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getDireccion(),
                passwordEncoder.encode(pwd));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).orElse(null));
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        Date date = new Date();
        usuario.setCreationDate(date);
        usuario.setLastUpdate(date);
        try {
            usuarioService.saveUsuario(usuario);
            EmailUserTemplate mailTemplate = new EmailUserTemplate();
            //MimeMessage msg = javaMailSender.createMimeMessage();
            //MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            //helper.setFrom("no-reply@repipa.org");
            //helper.setTo("csanchez4.91@gmail.com");
            //helper.setSubject("REPIPA - Nuevo usuario");
            //helper.setText(mailTemplate.newUser(nuevoUsuario.getNombre(), pwd),true);
			   /* Parte de codigo que adjunta un archivo al correo que se envía

			   File file = new File("C:/Users/CSA/holi.pdf");
			   if (file.exists() && !file.isDirectory()) {
				   System.out.println("Ubica bien el archivo");
				   helper.addAttachment("bienvenido.xlsx", file);
			   }
			   */
            //javaMailSender.send(msg);

        } catch (Exception e) {
            System.out.println("Error - "+e);
        }
        return new ResponseEntity(new Mensaje("Usuario creado. Enviando tus credenciales por correo.."),
                HttpStatus.CREATED);
    }

/*    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Validated @RequestBody String email) {
        if (usuarioService.existsByEmail(email)) {
            Usuario userWhoWantsToResetPassword = usuarioService.findByEmail(email).get();
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+?";
            String pwd = RandomStringUtils.random(10, characters);
            userWhoWantsToResetPassword.setPassword(passwordEncoder.encode(pwd));
            //userWhoWantsToResetPassword.setFirstLogging(true);
            try {
                usuarioService.saveUsuario(userWhoWantsToResetPassword);
                SimpleMailMessage msg = new SimpleMailMessage();
                EmailUserTemplate mailTemplate = new EmailUserTemplate();
                msg.setTo("csanchez4.91@gmail.com");
                msg.setFrom("no-reply@repipa.org");
                msg.setSubject("REPIPA - Reset password");
                msg.setText(mailTemplate.resetPassword(pwd));
                javaMailSender.send(msg);
            } catch (Exception e) {
            }
            return new ResponseEntity(new Mensaje("contraseña enviada por correo electrónico"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(new Mensaje("No existe ningún usuario con las iniciales proporcionadas."),
                    HttpStatus.BAD_REQUEST);
        }
    }*/

   // @SuppressWarnings({ "unchecked", "rawtypes" })
/*    @PutMapping("/change-password/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> change_password(@RequestBody Usuario newPassword, @PathVariable int id,
                                             @RequestHeader(name = "Authorization") String token) {
        String userToken = jwtProvider.getNombreUsuarioFromToken(token.replace("Bearer ", ""));
        Usuario userWhoWantsToChangePassword = usuarioService.findById(id);
        String quienLaQuiereCambiarFRONT = newPassword.getEmail();
        String quienLaQuiereCambiarBBDD = userWhoWantsToChangePassword.getEmail();
        if (userToken.equals(quienLaQuiereCambiarFRONT) && userToken.equals(quienLaQuiereCambiarBBDD)) {
            userWhoWantsToChangePassword.setPassword(passwordEncoder.encode(newPassword.getPassword()));
            usuarioService.guardar(userWhoWantsToChangePassword);

            return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.CREATED);
        } else {
          //  logger.info("Usuario " + userWhoWantsToChangePassword.getName()
            //        + " ha intentado cambiar la contraseña y no coincide la antigua");
            return new ResponseEntity(new Mensaje("Usuario y token no coinciden"), HttpStatus.FORBIDDEN);
        }
    }*/

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody @Valid final LoginUsuario loginUsuario){
        UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword());

        Authentication authentication = authenticationManager.authenticate(
               u);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginUsuario.getRememberMe() == null) ? false : loginUsuario.getRememberMe();
        String jwt = jwtProvider.generateToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);


        Usuario user = usuarioService.findByEmail(loginUsuario.getNombreUsuario()).orElse(null);
        user.setLastLogging(new Date());
        usuarioService.saveUsuario(user);
        //logger.info("Usuario " + userDetails.getUsername() + " se ha logeado");

        return new ResponseEntity<>(new JwtDTO(jwt), httpHeaders, HttpStatus.OK);
    }

   /* @PostMapping("/sendCode")
    public ResponseEntity sendCode(@RequestBody String username) throws MessagingException {
        try {

            User user = usuarioService.getByNombreUsuario(username);
            long id = user.getId();

            if(user == null)
                return ResponseEntity.status(404).body("Not Found");

            AuthCode authCode = authCodeService.generateCode(user);
            logger.info(user.getUserId()+ " ha solicitado código para cambiar contraseña");
            return ResponseEntity.status(200).body(authCode);
        }catch(Exception e) {
            logger.error("No se ha podido enviar código de cambio de contraseña");
            return ResponseEntity.status(400).body("Bad Request");
        }

    }

    @PostMapping("/verifyCode/{username}")
    public ResponseEntity verifyCode(@RequestBody String code, @PathVariable String username) {
        User user = usuarioService.getByNombreUsuario(username);
        long id = user.getId();

        if(user == null)
            return ResponseEntity.status(404).body("Not Found");
        try {
            authCodeService.verifyCode(user, code);
        } catch(Exception e) {
            logger.warn("Código de verificación invalido. "+ e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }

        try {
            usuarioService.ResetPassword(user);
            JSONObject object = new JSONObject();
            object.put("message", "Nueva contraseña enviado al correo");
            logger.info(user.getUserId()+ " ha reseteado su contraseña");
            return ResponseEntity.status(200).body(object.toString());
        }catch(Exception e) {
            logger.error(user.getUserId()+ " no pudo resetear su contraseña "+ e.getMessage());
            return ResponseEntity.status(400).body("Bad Request");
        }*/
    }

