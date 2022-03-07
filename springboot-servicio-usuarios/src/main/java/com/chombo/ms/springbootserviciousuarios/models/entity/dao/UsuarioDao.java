package com.chombo.ms.springbootserviciousuarios.models.entity.dao;

import com.chombo.ms.springbootserviciousuarios.models.entity.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    /*
     * Si en un método no se le especifíca el path en @RestResource entonces tomará el nombre del método.
     */
    @RestResource(path = "buscar-username")
    public Usuario findByUsername(@Param("username") String username);

    @Query("select u from Usuario u where u.username = ?1")
    public Usuario obtenerPorUsername(String username);
}
