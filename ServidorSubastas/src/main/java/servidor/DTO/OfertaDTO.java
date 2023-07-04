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

public class OfertaDTO implements Serializable {
    private LoginDTO compradorActual;
    private float valor;

    public OfertaDTO(LoginDTO compradorActual, float valor) {
        this.compradorActual = compradorActual;
        this.valor = valor;
    }

    public LoginDTO getCompradorActual() {
        return compradorActual;
    }

    public void setCompradorActual(LoginDTO compradorActual) {
        this.compradorActual = compradorActual;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
