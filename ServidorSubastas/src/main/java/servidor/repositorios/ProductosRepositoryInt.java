
package servidor.repositorios;

import java.util.List;
import servidor.DTO.ProductoDTO;
import servidor.DTO.UsuarioDTO;

public interface ProductosRepositoryInt
{    
    public boolean registrarProducto(ProductoDTO objProducto);
    public List<ProductoDTO> listarProductosParaOfertar();
    
    List<ProductoDTO> listarProductos();
    ProductoDTO consultarProducto(String codigo); 
}


