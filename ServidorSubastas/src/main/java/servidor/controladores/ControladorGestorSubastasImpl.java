/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.controladores;

/**
 *
 * @author Kevin
 */

import cliente.controladores.ControladorCallBackInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import servidor.DTO.LoginDTO;
import servidor.DTO.OfertaDTO;
import servidor.DTO.ProductoDTO;
import servidor.DTO.SubastaDTO;
import servidor.DTO.UsuarioDTO;
import servidor.repositorios.ProductosRepositoryInt;
import servidor.repositorios.SubastasRepositoryInt;

public class ControladorGestorSubastasImpl extends UnicastRemoteObject implements ControladorGestorSubastasInt {
    private final SubastasRepositoryInt subastasRepository;
    private final ProductosRepositoryInt productosRepository;

    public ControladorGestorSubastasImpl(SubastasRepositoryInt subastasRepository, ProductosRepositoryInt productosRepository)throws RemoteException {
        this.subastasRepository = subastasRepository;
        this.productosRepository = productosRepository;
    }

    
    @Override
    public List<ProductoDTO> listarProductos() throws RemoteException {
        return productosRepository.listarProductos();
    }

    @Override
    public ProductoDTO consultarProducto(String codigo) throws RemoteException {
        return productosRepository.consultarProducto(codigo);
    }

    @Override
    public SubastaDTO consultarProductoEnSubasta() throws RemoteException {
        return subastasRepository.consultarProductoEnSubasta();
    }

    @Override
    public boolean ofertar(OfertaDTO oferta) throws RemoteException {
        return subastasRepository.ofertar(oferta);
    }

    @Override
    public boolean suscribirUsuario(ControladorCallBackInt referencia) throws RemoteException {
        return subastasRepository.suscribirUsuario(referencia);
    }

}

