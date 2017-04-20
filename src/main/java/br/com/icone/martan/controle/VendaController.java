/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ItemPedido;
import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.TipoPedido;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Gleywson
 */
@Named(value = "vendaController")
@SessionScoped
public class VendaController implements Serializable {

    private Pedido venda;
    private ItemPedido item;
    private PedidoFacade repositorio;
    
    
    public VendaController() {
        venda = new Pedido();
        venda.setTipo(TipoPedido.VENDA);
        item = new ItemPedido();
    }

    public Pedido getVenda() {
        return venda;
    }

    public void setVenda(Pedido venda) {
        this.venda = venda;
    }
    
    
    
}
