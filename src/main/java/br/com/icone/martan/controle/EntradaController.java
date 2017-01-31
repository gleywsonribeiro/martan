/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Entrada;
import br.com.icone.martan.modelo.Fornecedor;
import br.com.icone.martan.modelo.TipoDocumentoFiscal;
import br.com.icone.martan.modelo.repositorio.EntradaFacade;
import br.com.icone.martan.modelo.repositorio.FornecedorFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "entradaController")
@SessionScoped
public class EntradaController implements Serializable {

    private Entrada entrada;
    private List<Entrada> entradas;
    
    @Inject
    private EntradaFacade repositorio;
    @Inject
    private FornecedorFacade repositorioFornecedor;
    
    public EntradaController() {
        this.entrada = new Entrada();
    }
    
    public void salvar() {
        if(entrada.getId() == null) {
            repositorio.create(entrada);
        } else {
            repositorio.edit(entrada);
        }
//        entrada = new Entrada();
        entradas = null;
    }
    
    public void novo() {
        this.entrada = new Entrada();
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }
    
    public List<Fornecedor> getFornecedores() {
        return repositorioFornecedor.findAll();
    }
    
    public TipoDocumentoFiscal[] getDocFiscais() {
        return TipoDocumentoFiscal.values();
    }

    public List<Entrada> getEntradas() {
        if(entradas == null) {
            entradas = repositorio.findAll();
        }
        return entradas;
    }
    
    
}
