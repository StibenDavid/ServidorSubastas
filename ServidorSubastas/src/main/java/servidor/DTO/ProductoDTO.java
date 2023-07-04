package servidor.DTO;

import java.io.Serializable;

public class ProductoDTO implements Serializable {
    public enum EstadoSubasta {
        SUBASTANDO,
        NO_SUBASTANDO,
        VENDIDO
    }
    private String codigo;
    private String nombre;
    private float valorInicialOferta;
    private EstadoSubasta estado;

    public ProductoDTO(String codigo, String nombre, float valorInicialOferta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.valorInicialOferta = valorInicialOferta;
        this.estado = EstadoSubasta.NO_SUBASTANDO; // Valor por defecto
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValorInicialOferta() {
        return valorInicialOferta;
    }

    public void setValorInicialOferta(float valorInicialOferta) {
        this.valorInicialOferta = valorInicialOferta;
    }
    
    public EstadoSubasta getEstado() {
        return estado;
    }

    public void setEstado(EstadoSubasta estado) {
        this.estado = estado;
    }
    
}
