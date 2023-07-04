/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.LoginDTO;
import servidor.DTO.UsuarioDTO;

/**
 *
 * @author Kevin
 */
public interface ControladorGestorConsultaInt extends Remote  {
    
    public UsuarioDTO consultarUsuario(LoginDTO login) throws RemoteException;
}
