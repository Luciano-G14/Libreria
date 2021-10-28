/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreriaSB.servicios;

import egg.web.libreriaSB.entidades.Autor;
import egg.web.libreriaSB.entidades.Editorial;
import egg.web.libreriaSB.excepciones.ExcepcionServicio;
import egg.web.libreriaSB.repositorios.EditorialRepositorio;
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
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre)throws ExcepcionServicio{
        if (nombre==null || nombre.isEmpty()) {
            throw new ExcepcionServicio("El nombre esta vacio");
        }
    Editorial editorial= new Editorial();
    editorial.setNombre(nombre);
    editorial.setAlta(Boolean.TRUE);
    editorialRepositorio.save(editorial);
    }
    
    @Transactional
    public void modificar(String id,String nombre)throws ExcepcionServicio{
    if (nombre==null || nombre.isEmpty()) {
            throw new ExcepcionServicio("El nombre esta vacio");
        }
    if (id==null || id.isEmpty()) {
            throw new ExcepcionServicio("El id esta vacio");
        }
    Optional<Editorial> respuesta= editorialRepositorio.findById(id);
    if (respuesta.isPresent()) {
            Editorial editorial=respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }else{
    throw new ExcepcionServicio("No se encontro la editorial solicitado");
    }
    }
    @Transactional
    public void deshabilitar(String id) throws ExcepcionServicio{
    if (id==null || id.isEmpty()) {
            throw new ExcepcionServicio("El id esta vacio");
        }
    Optional<Editorial> respuesta= editorialRepositorio.findById(id);
    if (respuesta.isPresent()) {
            Editorial editorial=respuesta.get();
            editorial.setAlta(Boolean.FALSE);
            editorialRepositorio.save(editorial);
        }else{
    throw new ExcepcionServicio("No se encontro la editorial solicitado");
    }
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> buscaTodos(){
    return editorialRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public Editorial buscarPorId(String id){
    Optional<Editorial> editorialOptional = editorialRepositorio.findById(id);
    return editorialOptional.orElse(null);
    }
}
