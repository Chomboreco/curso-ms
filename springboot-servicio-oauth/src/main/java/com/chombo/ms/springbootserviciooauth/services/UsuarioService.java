package com.chombo.ms.springbootserviciooauth.services;

import java.util.List;
import java.util.stream.Collectors;

import com.chombo.ms.springbootserviciooauth.clients.UsuarioFeignClient;
import com.chombo.ms.springbootserviciousuarioscommons.models.entity.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsuarioFeignClient client;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = client.findByUsername(username);

        if (usuario == null) {
            log.error("Error en el login, no existe el usario " + username + " en el sistema");
            throw new UsernameNotFoundException(
                    "Error en el login, no existe el usario " + username + " en el sistema");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        log.info("Usuario autenticado: " + username);

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
                true, true, true, authorities);
    }

    @Override
    public Usuario findByUsername(String username) {
        return client.findByUsername(username);
    }
}
