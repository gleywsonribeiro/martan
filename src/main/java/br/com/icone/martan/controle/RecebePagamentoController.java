/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ContaReceber;
import br.com.icone.martan.modelo.FormaPagamento;
import br.com.icone.martan.modelo.repositorio.ContaReceberFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gleywson
 */
@Named(value = "recebePagamentoController")
@SessionScoped
public class RecebePagamentoController implements Serializable {

    private ContaReceber conta;
//    private List<ContaReceber> contas;
    @Inject
    private ContaReceberFacade repositorioConta;
    
    public RecebePagamentoController() {
        this.conta = new ContaReceber();
    }

    public ContaReceber getConta() {
        return conta;
    }

    public void setConta(ContaReceber conta) {
        this.conta = conta;
    }

    public List<ContaReceber> getContas() {
        return repositorioConta.findAll();
    }
    
    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<String, Object>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 300);
        opcoes.put("contentWidth", 400);

        RequestContext.getCurrentInstance().openDialog("/telas/financeiro/contareceber/dialogo/dlgpagamento", opcoes, null);
    }
    
    
    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }
    
}
