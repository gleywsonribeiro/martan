/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gleywson
 */
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    Categoria categoria;
    //Unidade
    //Barcode
    @Column(nullable = false, length = 75)
    private String nome;

    @Column(nullable = false, name = "qtd_estoque")
    private int quantidadeEstoque;

    @Column(nullable = false, name = "vl_venda")
    private double valorVenda;

    @Column(nullable = false, name = "vl_custo")
    private double valorCusto;

    @Column(nullable = false, name = "estoque_min")
    private int estoqueMinimo;

    @Column(nullable = false, name = "percent_lucro")
    private float percentualLucro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public float getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(float percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    public void calculaValorVenda() {
        double valor = getValorCusto() + getValorCusto() * getPercentualLucro() / 100;
        setValorVenda(valor);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.iconeinformartica.martan.modelo.Produto[ id=" + id + " ]";
    }

}
