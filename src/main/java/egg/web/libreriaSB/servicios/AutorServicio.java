/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreriaSB.servicios;

import egg.web.libreriaSB.entidades.Autor;
import egg.web.libreriaSB.excepciones.ExcepcionServicio;
import egg.web.libreriaSB.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Luciano
 */
@Service
public class AutorServicio {
   @Autowired
    private AutorRepositorio autorRepositorio;
    
   @Transactional
    public void crearAutor(String nombre)throws ExcepcionServicio{
        if (nombre==null || nombre.isEmpty()) {
            throw new ExcepcionServicio("El nombre esta vacio");
        }
    Autor autor= new Autor();
    autor.setNombre(nombre);
    autor.setAlta(Boolean.TRUE);
    autorRepositorio.save(autor);
    }
    
    @Transactional
    public void modificar(String id,String nombre)throws ExcepcionServicio{
    if (nombre==null || nombre.isEmpty()) {
            throw new ExcepcionServicio("El nombre esta vacio");
        }
    if (id==null || id.isEmpty()) {
            throw new ExcepcionServicio("El id esta vacio");
        }
    Optional<Autor> respuesta= autorRepositorio.findById(id);
    if (respuesta.isPresent()) {
            Autor autor=respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }else{
    throw new ExcepcionServicio("No se encontro el autor solicitado");
    }
    }
    @Transactional
    public void deshabilitar(String id) throws ExcepcionServicio{
    if (id==null || id.isEmpty()) {
            throw new ExcepcionServicio("El id esta vacio");
        }
    Optional<Autor> respuesta= autorRepositorio.findById(id);
    if (respuesta.isPresent()) {
            Autor autor=respuesta.get();
            autor.setAlta(Boolean.FALSE);
            autorRepositorio.save(autor);
        }else{
    throw new ExcepcionServicio("No se encontro el autor solicitado");
    }
    }
    
    @Transactional(readOnly = true)
    public List<Autor> buscaTodos(){
    return autorRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public Autor buscarPorId(String id){
    Optional<Autor> autorOptional = autorRepositorio.findById(id);
    return autorOptional.orElse(null);
    }
    
}
