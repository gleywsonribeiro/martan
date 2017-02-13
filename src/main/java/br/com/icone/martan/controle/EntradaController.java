/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Entrada;
import br.com.icone.martan.modelo.Fornecedor;
import br.com.icone.martan.modelo.ItemEntrada;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.TipoDocumentoFiscal;
import br.com.icone.martan.modelo.repositorio.EntradaFacade;
import br.com.icone.martan.modelo.repositorio.FornecedorFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "entradaController")
@SessionScoped
public class EntradaController implements Serializable {

    private Entrada entrada;
    private List<Entrada> entradas;

    private ItemEntrada item;

    @Inject
    private EntradaFacade repositorio;
    @Inject
    private FornecedorFacade repositorioFornecedor;
    @Inject
    private ProdutoFacade repositorioProduto;

    public EntradaController() {
        novo();
    }

    public void salvar() {
        
        for(ItemEntrada itemDaNota:entrada.getItens()) {
            Produto p = itemDaNota.getProduto();
            int quantidade  = itemDaNota.getQuantidade();
            p.adicionar(quantidade);
            repositorioProduto.edit(p);
        }
        
        if (entrada.getId() == null) {
            repositorio.create(entrada);
            JsfUtil.addSuccessMessage("Entrada registrada com sucesso!");
        } else {
            repositorio.edit(entrada);
            JsfUtil.addSuccessMessage("Entrada alterada com sucesso!");
        }
//        entrada = new Entrada();

        entradas = null;
    }

    public void adicionarItem() {
        if (entrada.isExisteItem(item)) {
            JsfUtil.addWarnMessage("Item já existe!");
        } else {
            entrada.getItens().add(item);
            item.setEntrada(entrada);
        }

        entrada.recalcularTotalNota();
        this.item = new ItemEntrada();
    }

    public void removerItem() {
        entrada.getItens().remove(item);
        this.item = new ItemEntrada();
        entrada.recalcularTotalNota();
    }

    public void novo() {
        this.entrada = new Entrada();
        this.item = new ItemEntrada();
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public List<Fornecedor> getFornecedores() {
        return repositorioFornecedor.findAll();
    }

    public TipoDocumentoFiscal[] getDocFiscais() {
        return TipoDocumentoFiscal.values();
    }

    public List<Entrada> getEntradas() {
        if (entradas == null) {
            entradas = repositorio.findAll();
        }
        return entradas;
    }

    public ItemEntrada getItem() {
        return item;
    }

    public void setItem(ItemEntrada item) {
        this.item = item;
    }

    public List<Produto> buscaProdutoDescricao(String descricao) {
        return repositorioProduto.getProdutosPorDescricao(descricao);
    }

}
