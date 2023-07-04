/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

import java.io.Serializable;

/**
 *
 * @author Kevin
 */

public class SubastaDTO implements Serializable {
    public enum EstadoSubasta {
        NUEVA,
        ABIERTA,
        CERRADA
    }
    private ProductoDTO producto;
    private OfertaDTO ofertaActual;
    private EstadoSubasta estado;

    public SubastaDTO(ProductoDTO producto, OfertaDTO ofertaActual) {
        this.producto = producto;
        this.ofertaActual = ofertaActual;
        this.estado = EstadoSubasta.NUEVA;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public OfertaDTO getOfertaActual() {
        return ofertaActual;
    }

    public void setOfertaActual(OfertaDTO ofertaActual) {
        this.ofertaActual = ofertaActual;
    }

    public EstadoSubasta getEstado() {
        return estado;
    }

    public void setEstado(EstadoSubasta estado) {
        this.estado = estado;
    }
}
