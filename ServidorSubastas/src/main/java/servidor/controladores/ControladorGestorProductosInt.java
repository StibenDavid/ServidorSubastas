

package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.ProductoDTO;
import servidor.DTO.UsuarioDTO;
//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorProductosInt extends Remote
{
    public boolean registrarProducto(ProductoDTO objProducto) throws RemoteException;
    public List<ProductoDTO> listarProductosParaOfertar() throws RemoteException; 
    public UsuarioDTO abrirCerrarSubasta(ProductoDTO objProducto) throws RemoteException; 
}


