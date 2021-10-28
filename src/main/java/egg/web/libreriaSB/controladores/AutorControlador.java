/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreriaSB.controladores;

import egg.web.libreriaSB.entidades.Autor;
import egg.web.libreriaSB.excepciones.ExcepcionServicio;
import egg.web.libreriaSB.servicios.AutorServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Luciano
 */
@Controller
@RequestMapping("/autores")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping
    public ModelAndView mostrarTodos(){
    ModelAndView mav = new ModelAndView("autores");
    mav.addObject("autores", autorServicio.buscaTodos());
    return mav;
    }
    
    @GetMapping("/crear")
    public ModelAndView crearAutor(){
    ModelAndView mav = new ModelAndView("autor-formulario");
    mav.addObject("autor", new Autor());
    mav.addObject("action", "guardar");
    return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) throws ExcepcionServicio{
    autorServicio.crearAutor(nombre);
    return new RedirectView("/autores");
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", autorServicio.buscarPorId(id));
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String nombre,@RequestParam String id) throws ExcepcionServicio {
        autorServicio.modificar(id, nombre);
        return new RedirectView("/autores");
    }
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) throws ExcepcionServicio{
        autorServicio.deshabilitar(id);
        return new RedirectView("/autores");
    }
    
    
}
