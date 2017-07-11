/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo.repositorio;

import br.com.icone.martan.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gleywson
 */
@Stateless
public class UserEJB implements UserEJBRemote {

    @PersistenceContext(unitName = "martanPU")
    private EntityManager em;

    @Override
    public Usuario getUsuarioPorLogin(String login) {
        System.out.println("Metodo foi chamado");
        Usuario usuario = null;

        try {
            usuario = getEntityManager().createQuery("SELECT user FROM Usuario AS user WHERE user.login = :login", Usuario.class)
                    .setParameter("login", login.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
