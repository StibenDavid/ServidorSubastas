package servidor.repositorios;

import java.util.ArrayList;
import java.util.List;
import servidor.DTO.ProductoDTO;
import servidor.DTO.UsuarioDTO;

public class ProductosRepository implements ProductosRepositoryInt {
    private final ArrayList<ProductoDTO> misProductos;

    public ProductosRepository() {
        this.misProductos = new ArrayList<>();
        this.misProductos.add(new ProductoDTO("11", "papa", 20));  
        this.misProductos.add(new ProductoDTO("22", "arroz", 35));

    }

    @Override
    public boolean registrarProducto(ProductoDTO objProducto) {
        System.out.println("Entrando a registrar producto...");
        boolean bandera = false;
        
        if (this.misProductos.size() < 5 && !existeProducto(objProducto.getCodigo())) {
            bandera = this.misProductos.add(objProducto);
        }
        
        return bandera;
    }


    @Override
    public List<ProductoDTO> listarProductosParaOfertar() {
         System.out.println("Entrando a listar productos a ofertar...");
        List<ProductoDTO> productosParaOfertar = new ArrayList<>();
        
        for (ProductoDTO producto : misProductos) {
            if (producto.getEstado() == ProductoDTO.EstadoSubasta.NO_SUBASTANDO) {
                productosParaOfertar.add(producto);
            }
        }
        
        return productosParaOfertar;
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        System.out.println("Entrando a listar productos...");
        return this.misProductos;
    }

    @Override
    public ProductoDTO consultarProducto(String codigo) {
        System.out.println("Entrando a consultar producto...");
        ProductoDTO productoEncontrado = null;

        for (ProductoDTO producto : misProductos) {
            if (producto.getCodigo().equals(codigo)) {
                productoEncontrado = producto;
                break;
            }
        }

        return productoEncontrado;
    }
    private boolean existeProducto(String codigo) {
        for (ProductoDTO objProducto : misProductos) {
            if (objProducto.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    
}
