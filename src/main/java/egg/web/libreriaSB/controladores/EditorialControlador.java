/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreriaSB.controladores;

import egg.web.libreriaSB.entidades.Editorial;
import egg.web.libreriaSB.excepciones.ExcepcionServicio;
import egg.web.libreriaSB.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/editoriales")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping
    public ModelAndView mostrarTodos(){
    ModelAndView mav= new ModelAndView("editoriales");
    mav.addObject("editoriales", editorialServicio.buscaTodos());
    return mav;
    }
    
     @GetMapping("/crear")
    public ModelAndView crearEditorial(){
    ModelAndView mav= new ModelAndView("editorial-formulario");
    mav.addObject("editorial",new Editorial());
    mav.addObject("action", "guardar");
    return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) throws ExcepcionServicio{
    editorialServicio.crearEditorial(nombre);
    return new RedirectView("/editoriales");
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarEditorial(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("editorial-formulario");
        mav.addObject("editorial", editorialServicio.buscarPorId(id));
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String nombre,@RequestParam String id) throws ExcepcionServicio {
        editorialServicio.modificar(id, nombre);
        return new RedirectView("/editoriales");
    }
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) throws ExcepcionServicio{
        editorialServicio.deshabilitar(id);
        return new RedirectView("/editoriales");
    }
}
