/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "inventarioController")
@ViewScoped
public class InventarioController implements Serializable{

    private Produto produto;
    private List<Produto> produtos;
    
    @Inject
    private ProdutoFacade repositorio;
            
    public InventarioController() {
        this.produto = new Produto();
    }
    
    public void atualizar() {
        repositorio.edit(produto);
        this.produto = new Produto();
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
