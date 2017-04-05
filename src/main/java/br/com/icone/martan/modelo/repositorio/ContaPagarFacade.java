/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo.repositorio;

import br.com.icone.martan.modelo.ContaPagar;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gleywson
 */
@Stateless
public class ContaPagarFacade extends AbstractFacade<ContaPagar> implements Serializable {

    @PersistenceContext(unitName = "martanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaPagarFacade() {
        super(ContaPagar.class);
    }
    
}
