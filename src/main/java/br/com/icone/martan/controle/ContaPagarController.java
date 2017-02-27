/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ContaPagar;
import br.com.icone.martan.modelo.repositorio.ContaPagarFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "contaPagarController")
@SessionScoped
public class ContaPagarController implements Serializable {
    @Inject
    private ContaPagarFacade repositorio;
    
    private ContaPagar conta;
    private List<ContaPagar> contas;
    
    public ContaPagarController() {
        conta = new ContaPagar();
    }

    public ContaPagar getConta() {
        return conta;
    }

    public void setConta(ContaPagar conta) {
        this.conta = conta;
    }

    public List<ContaPagar> getContas() {
        if(contas == null) {
            contas = repositorio.findAll();
        }
        return contas;
    }
    
    public void salvar() {
        if(conta.getId() == null) {
            repositorio.create(conta);
        } else {
            repositorio.edit(conta);
        }
        JsfUtil.addMessage("Conta salva com sucesso!");
    }
}
