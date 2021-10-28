/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreriaSB.repositorios;

import egg.web.libreriaSB.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luciano
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String>{
    @Modifying
    @Query("UPDATE Libro l SET l.nombre = :nombre WHERE l.id = :id")
    void modificar(@Param("nombre") String nombre);
}
