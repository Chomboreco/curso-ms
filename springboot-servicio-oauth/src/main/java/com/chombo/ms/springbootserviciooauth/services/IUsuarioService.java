package com.chombo.ms.springbootserviciooauth.services;

import com.chombo.ms.springbootserviciousuarioscommons.models.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
