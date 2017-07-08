/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo.repositorio;

import br.com.icone.martan.modelo.Usuario;
import javax.ejb.Local;

/**
 *
 * @author Gleywson
 */
@Local
public interface UserEJBLocal {
    public Usuario getUsuarioPorLogin(String login);
}
