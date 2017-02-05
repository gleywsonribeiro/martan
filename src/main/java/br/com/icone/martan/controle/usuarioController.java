/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.UsuarioFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "usuarioController")
@ViewScoped
public class usuarioController implements Serializable{
    
    private Usuario usuario;
    private List<Usuario> usuarios;
    
    @Inject
    private UsuarioFacade repositorio;
    
    public usuarioController() {
        this.usuario = new Usuario();
    }
    
    public void salvar() {
        if(usuario.getId() == null) {
            repositorio.create(usuario);
        } else {
            repositorio.edit(usuario);
        }
        JsfUtil.addSuccessMessage("Salvo com sucesso!");
        this.usuario = new Usuario();
        this.usuarios = null;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        if(usuarios == null) {
            usuarios = repositorio.findAll();
        }
        return usuarios;
    }
    
    
    
}

