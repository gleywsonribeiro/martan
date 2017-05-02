/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Pagamento;
import br.com.icone.martan.modelo.FormaPagamento;
import br.com.icone.martan.modelo.repositorio.PagamentoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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

    private Pagamento conta;
//    private List<ContaReceber> contas;
    @Inject
    private PagamentoFacade repositorioConta;
    
    public RecebePagamentoController() {
        this.conta = new Pagamento();
    }

    public Pagamento getConta() {
        return conta;
    }

    public void setConta(Pagamento conta) {
        this.conta = conta;
    }

    public List<Pagamento> getContas() {
        return repositorioConta.getContasEmitidas();
    }
    
    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<String, Object>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 400);
        opcoes.put("contentWidth", 400);

        RequestContext.getCurrentInstance().openDialog("/telas/financeiro/contareceber/dialogo/dlgpagamento", opcoes, null);
    }
    
    public void fecharVenda() {
        RequestContext.getCurrentInstance().closeDialog(null);
        conta.setDataPagamento(new Date());
        repositorioConta.edit(conta);
        
        
    }
    
    public boolean getAindaFalta() {
        return conta.getTroco().compareTo(BigDecimal.ZERO) < 0;
    }
    
    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }
    
}
