package com.chombo.ms.springbootserviciousuarios;

import com.chombo.ms.springbootserviciousuarios.models.entity.Role;
import com.chombo.ms.springbootserviciousuarios.models.entity.Usuario;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    /*
     * Esta configuración permite que los Id's de las entidades se muestren en el
     * JSON.
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Usuario.class, Role.class);
    }
}
