/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "pedidoController")
@SessionScoped
public class PedidoController implements Serializable {

    @Inject
    private LoginController lc;
    
    @Inject
    private PedidoFacade repositorio;
    
    private Pedido pedido;

    public PedidoController() {
        this.pedido = new Pedido();
        this.pedido.setDataCriacao(new Date());
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public void salvar() {
        if(pedido.getId() == null) {
            repositorio.create(pedido);
        } else {
            repositorio.edit(pedido);
        }
    }
    
}
