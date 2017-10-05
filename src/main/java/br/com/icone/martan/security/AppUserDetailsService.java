/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.security;

import br.com.icone.martan.modelo.Grupo;
import br.com.icone.martan.modelo.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import br.com.icone.martan.modelo.repositorio.UserEJBRemote;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gleywson
 */
@Controller
public class AppUserDetailsService implements UserDetailsService {
    
    private UserEJBRemote repositorio;

    public AppUserDetailsService() {
        try {
            InitialContext ctx = new InitialContext();
            repositorio = (UserEJBRemote) ctx.lookup("br.com.icone.martan.modelo.repositorio.UserEJBRemote");
        } catch (NamingException ex) {
            Logger.getLogger(UserEJBRemote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repositorio.getUsuarioPorLogin(login);
        UsuarioSistema user = null;

        if (usuario != null) {
            user = new UsuarioSistema(usuario, getGrupos(usuario));
        }

        return user;

    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        for (Grupo grupo : usuario.getGrupos()) {
            authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
        }

        return authorities;
    }

    public UserEJBRemote getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(UserEJBRemote repositorio) {
        this.repositorio = repositorio;
    }

}
