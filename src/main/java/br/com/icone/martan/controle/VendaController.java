/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.ContaReceber;
import br.com.icone.martan.modelo.FormaPagamento;
import br.com.icone.martan.modelo.ItemPedido;
import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.StatusPedido;
import br.com.icone.martan.modelo.TipoPedido;
import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.ContaReceberFacade;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.modelo.repositorio.UsuarioFacade;
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

    @Inject
    private UsuarioFacade usuarioRepository;
    
    @Inject
    private ContaReceberFacade repositorioCR;

    public VendaController() {
        novaVenda();
    }

    private void novaVenda() {
        venda = new Pedido();
        venda.setTipo(TipoPedido.VENDA);
        item = new ItemPedido();
        venda.setStatus(StatusPedido.EMITIDO);
    }

    public void emitir() {
        if (venda.getItens().isEmpty()) {
            JsfUtil.addWarnMessage("Adicione pelo menos um item");
        } else {
            boolean possuiTodosItens = true;

            for (ItemPedido itemDoCarrinho : venda.getItens()) {
                if (itemDoCarrinho.isEstoqueInsuficiente()) {
                    possuiTodosItens = false;
                    break;
                }
            }

            if (possuiTodosItens) {
                for (ItemPedido itemDoCarrinho : venda.getItens()) {
//                    itemDoCarrinho.getProduto().baixar(itemDoCarrinho.getQuantidade());
                    Produto produto = itemDoCarrinho.getProduto();
                    produto.baixar(itemDoCarrinho.getQuantidade());

                    produtoRepository.edit(produto);
                }
                this.venda.setStatus(StatusPedido.EMITIDO);
                if (venda.isNovo()) {
                    repositorio.create(venda);
                } else {
                    repositorio.edit(venda);
                }
                
                
                ContaReceber conta = new ContaReceber();
                conta.setPedido(venda);
                conta.setValor(venda.getValorTotal());
                conta.setDesconto(venda.getValorDesconto());
                
                repositorioCR.create(conta);
                
                JsfUtil.addMessage("Venda emitida com sucesso!");
                novaVenda();

            } else {
                JsfUtil.addWarnMessage("Não há estoque suficiente para esta venda! Reveja o carrinho.");
            }
        }
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

    public List<Usuario> getVendedores() {
        return usuarioRepository.findAll();
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public void recalcularPedido() {
        this.venda.recalcularValorTotal();
    }

    public List<Produto> buscaProdutoDescricao(String descricao) {
        return produtoRepository.getProdutosPorDescricao(descricao);
    }

    public void removerItem() {
        venda.getItens().remove(item);
        this.item = new ItemPedido();
        this.venda.recalcularValorTotal();
    }

    public void adicionar() {
        if (venda.isJaExiste(item)) {
            //Já adiciona logo pra não ter que percorrer a lista de novo para ajustar a quantidade
        } else {
            this.item.setPedido(venda);
            this.venda.getItens().add(item);
        }

        item = new ItemPedido();
        this.venda.recalcularValorTotal();
    }

    public boolean isAdicionarAtivo() {
        return isNaoTemItem();
    }

    public boolean isTemItem() {
        return this.item.getProduto() != null;
    }

    public boolean isNaoTemItem() {
        return !isTemItem();
    }
}
