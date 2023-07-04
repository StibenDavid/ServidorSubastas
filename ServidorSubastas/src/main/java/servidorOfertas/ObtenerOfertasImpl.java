/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorOfertas;

import java.util.List;
import sop_corba.OfertasIntPOA;
import servidor.repositorios.SubastasRepository;

public class ObtenerOfertasImpl extends OfertasIntPOA {
    private SubastasRepository subastasRepository;

    public ObtenerOfertasImpl(SubastasRepository subastasRepository) {
        this.subastasRepository = subastasRepository;
    }

    @Override
    public float[] obtenerOfertas() {
        List<Float> listaOfertas = subastasRepository.obtenerOfertas();
        float[] Ofertas = new float[listaOfertas.size()];
       

        for (int i = 0; i < listaOfertas.size(); i++) {
            Ofertas[i] = listaOfertas.get(i);
        }

        return Ofertas;
    }
}