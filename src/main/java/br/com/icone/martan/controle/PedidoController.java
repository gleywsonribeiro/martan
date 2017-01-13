/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Cliente;
import br.com.icone.martan.modelo.Endereco;
import br.com.icone.martan.modelo.FormaPagamento;
import br.com.icone.martan.modelo.ItemPedido;
import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.ClienteFacade;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
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
    @Inject
    private ProdutoFacade produtoRepository;

    private Pedido pedido;
    
    
    private Produto produtoCorrente;

    private boolean usarEnderecoCliente;

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
        if (vendedores == null) {
            vendedores = usuarioRepositoy.findAll();
        }
        return vendedores;
    }
    
    public void novo() {
        this.pedido = new Pedido();
    }

    public void salvar() {
        if (pedido.isNovo()) {
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

    public boolean isUsarEnderecoCliente() {
        return usarEnderecoCliente;
    }

    public void setUsarEnderecoCliente(boolean usarEnderecoCliente) {
        this.usarEnderecoCliente = usarEnderecoCliente;
    }

    public Produto getProdutoCorrente() {
        return produtoCorrente;
    }

    public void setProdutoCorrente(Produto produtoCorrente) {
        this.produtoCorrente = produtoCorrente;
    }
    
    
    //Vai ser usado para selecionar o endereço do cliente e usar na entrega
    public void ajustarEndereco() {
        if (pedido.getCliente() != null) {
            if (usarEnderecoCliente) {
                pedido.setEnderecoEntrega(this.pedido.getCliente().getEndereco());
            } else {
                pedido.setEnderecoEntrega(new Endereco());
            }
            
        }
    }
    
    
    
    public List<Produto> buscaProdutoDescricao(String descricao) {
        return produtoRepository.getProdutosPorDescricao(descricao);
    }

}
