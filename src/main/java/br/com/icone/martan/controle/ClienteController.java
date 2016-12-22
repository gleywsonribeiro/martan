/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Cliente;
import br.com.icone.martan.modelo.TipoPessoa;
import br.com.icone.martan.modelo.repositorio.ClienteFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "clienteController")
@ViewScoped
public class ClienteController implements Serializable{

    private Cliente cliente;
    private List<Cliente> clientes;
    
    
    @Inject
    private ClienteFacade repositorio;
    
    public ClienteController() {
        cliente  = new Cliente();
    }
    
    public void salvar() {
        if(cliente.getId() == null) {
            repositorio.create(cliente);
        } else {
            repositorio.edit(cliente);
        }
        this.cliente = new Cliente();
        JsfUtil.addSuccessMessage("Cliente inserido com sucesso!");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        if(clientes == null) {
            clientes = repositorio.findAll();
        }
        return clientes;
    }
    
    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }
    
}
