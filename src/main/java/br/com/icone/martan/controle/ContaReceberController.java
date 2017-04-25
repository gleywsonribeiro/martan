/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ContaReceber;
import br.com.icone.martan.modelo.repositorio.ContaReceberFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "contaReceberController")
@ViewScoped
public class ContaReceberController implements Serializable {

    @Inject
    ContaReceberFacade repositorio;
    
    private ContaReceber conta;
    
    public ContaReceberController() {
        this.conta = new ContaReceber();
    }

    public ContaReceber getConta() {
        return conta;
    }

    public void setConta(ContaReceber conta) {
        this.conta = conta;
    }
    
    public void salvar() {
        if(conta.getId() == null) {
            repositorio.create(conta);
        }
        else {
            repositorio.edit(conta);
        }
        JsfUtil.addMessage("Conta salva com sucesso!");
        conta = new ContaReceber();
    }
    
}
