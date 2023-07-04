/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 package servidor.DTO;

import java.io.Serializable;

/**
 *
 * @author Kevin
 */

public class LoginDTO implements Serializable {
    private String login;
    private String contraseña;

    public LoginDTO(String login, String contraseña) {
        this.login = login;
        this.contraseña = contraseña;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
