/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.repositorios;

/**
 *
 * @author Kevin
 */
import cliente.controladores.ControladorCallBackInt;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import servidor.DTO.LoginDTO;
import servidor.DTO.OfertaDTO;
import servidor.DTO.ProductoDTO;
import servidor.DTO.SubastaDTO;
import servidor.DTO.UsuarioDTO;

public interface SubastasRepositoryInt {
    SubastaDTO consultarProductoEnSubasta();
    boolean ofertar(OfertaDTO oferta);
    boolean suscribirUsuario(ControladorCallBackInt referencia);
    public UsuarioDTO abrirCerrarSubasta(ProductoDTO objProducto);
    public void notificarUsuarios(SubastaDTO objSubasta, UsuarioDTO objCompradorFinal);
    public LinkedList<Float> obtenerOfertas();
}

