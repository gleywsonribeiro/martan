/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ContaPagar;
import br.com.icone.martan.modelo.repositorio.ContaPagarFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
    
    private ContaPagar conta;

    public pesquisaContaPagar() {
        this.conta = new ContaPagar();
    }

    public List<ContaPagar> getContas() {
        return repositorio.findAll();
    }

    public List<ContaPagar> getContasPendentes() {
        return repositorio.contasPendentes();
    }
    
    public void pagar() {
        this.conta.setDataPagamento(new Date());
        this.conta.setValorPago(conta.getValor());
        repositorio.edit(conta);
        this.conta = new ContaPagar();
        JsfUtil.addMessage("Conta paga com sucesso!");
    }
   

    public ContaPagar getConta() {
        return conta;
    }

    public void setConta(ContaPagar conta) {
        this.conta = conta;
    }
    
}
