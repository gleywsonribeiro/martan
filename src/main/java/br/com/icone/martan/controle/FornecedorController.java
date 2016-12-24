/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Fornecedor;
import br.com.icone.martan.modelo.TipoPessoa;
import br.com.icone.martan.modelo.repositorio.FornecedorFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "fornecedorController")
@ViewScoped
public class FornecedorController implements Serializable{
    
    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
    @Inject
    private FornecedorFacade repositorio;
    
    public FornecedorController() {
        this.fornecedor = new Fornecedor();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        if(this.fornecedores == null) {
            fornecedores = repositorio.findAll();
        }
        this.fornecedores = fornecedores;
    }
    
    public void salvar() {
        if(fornecedor.getId() == null) {
            repositorio.create(fornecedor);
        } else {
            repositorio.edit(fornecedor);
        }
        this.fornecedor = new Fornecedor();
        this.fornecedores = null;
        JsfUtil.addSuccessMessage("Fornecedor cadastrado com sucesso!");
    }

    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }
}
