/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.util.JsfUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gleywson
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private Usuario usuario;

    public LoginController() {
        this.usuario = new Usuario();
    }

    public String login() {
//        List<Usuario> lista = facade.findAll();
//
//        for (Usuario user : lista) {
//            if (user.getLogin().equals(usuario.getLogin()) && user.getSenha().equals(usuario.getSenha())) {
//                FacesContext context = FacesContext.getCurrentInstance();
//                HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
//                this.usuario = user;
//                httpSession.setAttribute("currentUser", usuario);
//                return "index";
//            }
//        }

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bem vindo "+usuario.getLogin()));
        if (usuario.getLogin().equals("admin") && usuario.getSenha().equals("admin")) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
            //this.usuario = user;
            usuario.setNome("Gleywson");
            httpSession.setAttribute("currentUser", usuario);
            return "index?faces-redirect=true";
        } else {
            JsfUtil.addErrorMessage("Usuario ou senha inválidos!");
            return "";
        }

    }

    public void logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
        httpSession.setAttribute("currentUser", null);
        try {
            fc.getExternalContext().redirect(fc.getExternalContext().getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        httpSession.invalidate();
    }

    public String saudacao() {
        String saudacao = "Bom dia";
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        if (hora >= 12) {
            saudacao = "Boa tarde";
        }
        if (hora >= 18) {
            saudacao = "Boa noite";
        }
        saudacao +=  " " + usuario.getNome();
        return saudacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
