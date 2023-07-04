/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cliente.controladores;

import java.rmi.Remote; 
import java.rmi.RemoteException;
import servidor.DTO.SubastaDTO;
import servidor.DTO.UsuarioDTO;

/**
 *
 * @author Kevin
 */
public interface ControladorCallBackInt extends Remote {
    //Definicion del primer método remoto
    public void notificarCierreSubasta(SubastaDTO objSubasta, UsuarioDTO objComprador) throws RemoteException;
}
