/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.security;

import br.com.icone.martan.modelo.Grupo;
import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.UserEJBLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gleywson
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    
    private UserEJBLocal repositorio;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repositorio.getUsuarioPorLogin(login);
        UsuarioSistema user = null;
        
        if(usuario != null) {
            user = new UsuarioSistema(usuario, getGrupos(usuario));
        }
        System.out.println("Foi chamada");
        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        
        for(Grupo grupo : usuario.getGrupos()) {
            authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
        }
        
        return authorities;
    }

    public UserEJBLocal getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(UserEJBLocal repositorio) {
        this.repositorio = repositorio;
    }

    
    
}
