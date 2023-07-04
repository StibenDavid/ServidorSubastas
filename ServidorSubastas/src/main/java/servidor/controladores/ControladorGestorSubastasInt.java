/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;

/**
 *
 * @author Kevin
 */
import cliente.controladores.ControladorCallBackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.OfertaDTO;
import servidor.DTO.ProductoDTO;
import servidor.DTO.SubastaDTO;

public interface ControladorGestorSubastasInt extends Remote {
    List<ProductoDTO> listarProductos() throws RemoteException;
    ProductoDTO consultarProducto(String codigo) throws RemoteException;
    SubastaDTO consultarProductoEnSubasta() throws RemoteException;
    boolean ofertar(OfertaDTO oferta) throws RemoteException;
    boolean suscribirUsuario(ControladorCallBackInt referencia) throws RemoteException;
}

