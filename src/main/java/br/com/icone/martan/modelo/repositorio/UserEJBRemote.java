/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo.repositorio;
//java:global/martan/FornecedorFacade!br.com.icone.martan.modelo.repositorio.FornecedorFacade ->Exemplo de caminho ejb
import br.com.icone.martan.modelo.Usuario;
import javax.ejb.Remote;

/**
 *
 * @author Gleywson
 */
@Remote
public interface UserEJBRemote {
    public Usuario getUsuarioPorLogin(String login);
}
