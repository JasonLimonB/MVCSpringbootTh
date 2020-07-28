package com.jpa.example.controllers;

import com.jpa.example.dao.IClienteDao;
import com.jpa.example.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteControll {

    @Autowired
    @Qualifier("clienteDaoJPA")
    private IClienteDao clienteDao;

    @RequestMapping( value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    @RequestMapping( value = "/saveCliente", method = RequestMethod.GET )
    public String saveCliente(Map<String, Object> model){
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario al cliente");
        return "saveCliente";
    }

    @RequestMapping( value = "/saveCliente/{id}")
    public String editar(@PathVariable( value = "id" )Long id, Map<String, Object> model){

        Cliente cliente = null;

        if(id > 0){
            cliente = clienteDao.findOne(id);
        } else {
            return "redirect:listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar cliente");
        return "saveCliente";
    }

    @RequestMapping(value = "/saveCliente", method = RequestMethod.POST)
    public String guardar(Cliente cliente, SessionStatus status){
        clienteDao.guardarCliente(cliente);
        status.isComplete();
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar( @PathVariable(value = "id") Long id ){
        if( id > 0 ){
            clienteDao.delete(id);
        }
        return "redirect:/listar";
    }

}
