package com.chombo.ms.springbootserviciooauth.security.event;

import com.chombo.ms.springbootserviciooauth.services.IUsuarioService;
import com.chombo.ms.springbootserviciousuarioscommons.models.entity.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUsuarioService usuarioService;

    //@Autowired
    //private Tracer tracer;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            return;
        }

        /*
         * if (authentication.getName().equalsIgnoreCase("frontendapp")) {
         * return;
         * }
         */

        UserDetails user = (UserDetails) authentication.getPrincipal();
        log.info("Success Login: " + user.getUsername());

        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
            usuario.setIntentos(0);
            usuarioService.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String mensajeError = "Error Login: " + exception.getMessage();
        log.error(mensajeError);

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(mensajeError);
            // Obtenemos el usuario a través del getName()
            Usuario usuario = usuarioService.findByUsername(authentication.getName());

            if (usuario.getIntentos() == null) {
                usuario.setIntentos(0);
            }

            log.info("Intentos actuales es de: " + usuario.getIntentos());
            usuario.setIntentos(usuario.getIntentos() + 1);
            log.info("Intentos después es de: " + usuario.getIntentos());
            sb.append(" - Intentos de login: " + usuario.getIntentos());

            if (usuario.getIntentos() >= 3) {
                String erroMaximosIntentos = String.format("Usuario %s deshabilitado por máximos intentos", usuario.getUsername());
                log.error(erroMaximosIntentos);
                sb.append(" - " + erroMaximosIntentos);
                usuario.setEnabled(false);
            }

            usuarioService.update(usuario, usuario.getId());

            //tracer.currentSpan().tag("error.mensaje", sb.toString());
        } catch (FeignException e) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }
    }
}
