/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "cadastroProdutoController")
@SessionScoped
public class CadastroProdutoController implements Serializable {

    private Produto produto;
    private List<Produto> produtos;
    
    @Inject
    private ProdutoFacade repositorio;
    
    public CadastroProdutoController() {
        this.produto = new Produto();
    }
    
    public void salvar() {
        if(produto.getId() == null) {
            repositorio.create(produto);
        } else {
            repositorio.edit(produto);
        }
        this.produto = new Produto();
        this.produtos = null;
        JsfUtil.addSuccessMessage("Salvo com sucesso!");
    }
    
    public String novo() {
        this.produto = new Produto();
        return "cadastroProduto?faces-redirect=true";
    }
    
    public void remover() {
        
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        if(produtos == null) {
            produtos = repositorio.findAll();
        }
        return produtos;
    }
    
    
}
