/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Cliente;
import br.com.icone.martan.modelo.FormaPagamento;
import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.ClienteFacade;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import br.com.icone.martan.modelo.repositorio.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "pedidoController")
@SessionScoped
public class PedidoController implements Serializable {

    @Inject
    private PedidoFacade repositorio;
    @Inject
    private UsuarioFacade usuarioRepositoy;
    @Inject
    private ClienteFacade clienteRepository;
    
    private Pedido pedido;
    
    private List<Usuario> vendedores;

    public PedidoController() {
        this.pedido = new Pedido();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getVendedores() {
        if(vendedores == null) {
            vendedores = usuarioRepositoy.findAll();
        }
        return vendedores;
    }
    
    public void salvar() {
        if(pedido.getId() == null) {
            repositorio.create(pedido);
        } else {
            repositorio.edit(pedido);
        }
    }
    
    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }
    
    public List<Cliente> completarClientes(String nome) {
        return clienteRepository.getClientesPorNome(nome);
    }
}
