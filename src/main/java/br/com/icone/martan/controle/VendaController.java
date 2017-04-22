/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ItemPedido;
import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.TipoPedido;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "vendaController")
@ViewScoped
public class VendaController implements Serializable {

    private Pedido venda;
    private ItemPedido item;
    
    @Inject
    private PedidoFacade repositorio;
    
    @Inject
    private ProdutoFacade produtoRepository;
    
    public VendaController() {
        venda = new Pedido();
        venda.setTipo(TipoPedido.VENDA);
        item = new ItemPedido();
    }

    public void salvar() {
        if(venda.isNovo()) {
            repositorio.create(venda);
        }
        else {
            repositorio.edit(venda);
        }
        JsfUtil.addMessage("Venda salva!");
    }
    
    public Pedido getVenda() {
        return venda;
    }

    
    public void setVenda(Pedido venda) {
        this.venda = venda;
    }

    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }
    
    public List<Produto> buscaProdutoCodigo(String cod) {
        return produtoRepository.getProdutosPorCodigo(cod);
    }
    
}
