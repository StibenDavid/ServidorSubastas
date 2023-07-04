/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.controladores;

/**
 *
 * @author Kevin
 */


import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import servidor.DTO.ProductoDTO;
import servidor.DTO.SubastaDTO;
import servidor.DTO.UsuarioDTO;
import servidor.repositorios.ProductosRepositoryInt;
import servidor.repositorios.SubastasRepositoryInt;


public class ControladorGestorProductosImpl extends UnicastRemoteObject implements ControladorGestorProductosInt {
    private final ProductosRepositoryInt productosRepository;
    private final SubastasRepositoryInt subastasRepository;

    public ControladorGestorProductosImpl(ProductosRepositoryInt productosRepository, SubastasRepositoryInt subastasRepository) throws RemoteException {
        super();
        this.productosRepository = productosRepository;
        this.subastasRepository = subastasRepository;
    }
    
    @Override
    public boolean registrarProducto(ProductoDTO objProducto) throws RemoteException {
        // Implementación del registro de producto
        // Retorna true si se registra correctamente, de lo contrario false
        return productosRepository.registrarProducto(objProducto);
    }

    @Override
    public List<ProductoDTO> listarProductosParaOfertar() throws RemoteException {
        // Implementación de la obtención de la lista de productos
        // Retorna la lista de productos
        return productosRepository.listarProductosParaOfertar();
    }

    

    @Override
    public UsuarioDTO abrirCerrarSubasta(ProductoDTO objProducto) throws RemoteException {
        // Verificar si hay una subasta abierta para otro producto (consultarProductoSubastado)
        SubastaDTO SubastaActua = subastasRepository.consultarProductoEnSubasta();
        if (SubastaActua != null && SubastaActua.getEstado().equals(SubastaDTO.EstadoSubasta.ABIERTA)) {
            ProductoDTO objProductoActualSub = SubastaActua.getProducto();
            if (objProductoActualSub!=null && !objProductoActualSub.getCodigo().equals(objProducto.getCodigo())) {
                // Valor centinaela, -1 significa que quiero cerrar la subasta
                if (!objProducto.getCodigo().equals("-1")) {
                    
                    System.out.println("Ya hay una subasta abierta para otro producto, no se puede iniciar otra");
                    return null;
                }else{
                    // Cerrar la subasta actual
                    return subastasRepository.abrirCerrarSubasta(objProducto);
                }
            }
        }
        
        // Verificar si ya existe el producto especificado
        // Revisar si el producto existe en productosRepository 
        ProductoDTO productoExistente = productosRepository.consultarProducto(objProducto.getCodigo());
        // Abrir una nueva subasta para el producto con el codigo especificado
        if (productoExistente != null && productoExistente.getEstado().equals(ProductoDTO.EstadoSubasta.NO_SUBASTANDO)) {
            // Abrir una subasta para el producto con el usuario proporcionado si esta disponible para subastar
            return subastasRepository.abrirCerrarSubasta(productoExistente);
        } else {
            // El producto no existe
            return null;
        }
    }
    public void notificarUsuarios (SubastaDTO objSubasta, UsuarioDTO objompradorFinal){
        subastasRepository.notificarUsuarios(objSubasta,objompradorFinal);
    }
}
