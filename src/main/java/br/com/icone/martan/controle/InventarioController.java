/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Inventario;
import br.com.icone.martan.modelo.ItemInventario;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.InventarioFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "inventarioController")
@ViewScoped
public class InventarioController implements Serializable {

    @Inject
    private ProdutoFacade repositorio;
    
    
    @Inject
    private InventarioFacade inventarioRepository;
    private Inventario inventario;
    private List<Inventario> inventarios;
    
    @ManagedProperty(value = "#{loginController.usuario}")
    private Usuario usuario;

    public InventarioController() {
        this.inventario = new Inventario();
//        List<Produto> produtos = repositorio.findAll();
//        for (Produto produto : produtos) {
//            ItemInventario item = new ItemInventario();
//            item.setProduto(produto);
//            this.inventario.getItens().add(item);
//        }
    }
   
    

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Inventario> getInventarios() {
        if(inventarios == null) {
            inventarios = inventarioRepository.findAll();
        }
        return inventarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

//    public void atualizarEstoque() {
//        for (Produto item : produtos) {
//            repositorio.edit(item);
//        }
//        JsfUtil.addMessage("Inventário finalizado com sucesso!");
//    }

    
    public void salvar() {
        if(inventario.getId() == null) {
            inventarioRepository.create(inventario);
        }
        else {
            inventarioRepository.edit(inventario);
        }
        JsfUtil.addMessage("Salvo com sucesso!");
    }
    
}
