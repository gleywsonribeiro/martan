/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ContaPagar;
import br.com.icone.martan.modelo.repositorio.ContaPagarFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "pesquisaContaPagar")
@ViewScoped
public class pesquisaContaPagar implements Serializable {

    @Inject
    private ContaPagarFacade repositorio;
    
    private List<ContaPagar> contas;
    
    public pesquisaContaPagar() {
    }

    public List<ContaPagar> getContas() {
        if(contas == null) {
            contas = repositorio.findAll();
        }
        return contas;
    }
    
    
    
}
