/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.repositorios;

/**
 *
 * @author Kevin
 */
import cliente.controladores.ControladorCallBackInt;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.DTO.LoginDTO;
import servidor.DTO.OfertaDTO;
import servidor.DTO.ProductoDTO;
import servidor.DTO.SubastaDTO;
import servidor.DTO.UsuarioDTO;
import servidor.controladores.ControladorGestorConsultaInt;
import java.util.LinkedList;
import sop_corba.FacturaInt;
import sop_corba.FacturaIntPackage.facturaDTO;

public class SubastasRepository implements SubastasRepositoryInt {
    private SubastaDTO subastaActual;
    private final ControladorGestorConsultaInt objRemotoGestorConsulta;
    private final FacturaInt objRemotoFactura;
    private final LinkedList<ControladorCallBackInt> listaReferencias;    
    private final LinkedList<Float> listaOfertas;


    public SubastasRepository(servidor.controladores.ControladorGestorConsultaInt objRemotoGestorConsulta,FacturaInt objRemotoFactura) {
        this.subastaActual = new SubastaDTO(null, null);
        this.objRemotoGestorConsulta = objRemotoGestorConsulta;
        this.listaReferencias = new LinkedList<>();
        this.listaOfertas = new LinkedList<>();
        this.objRemotoFactura = objRemotoFactura;
        
    }

    @Override
    public SubastaDTO consultarProductoEnSubasta(){
        System.out.println("Entrando a consultar producto subastado...");
        return this.subastaActual;
    }

    @Override
    public boolean ofertar(OfertaDTO oferta) {
        System.out.println("Entrando a ofertar...");

        // Obtener la subasta actual
        SubastaDTO subasta = consultarProductoEnSubasta();

        if (subasta != null && subasta.getEstado().equals(SubastaDTO.EstadoSubasta.ABIERTA)) {
            // Verificar si la oferta es válida (mayor que la oferta actual)
            OfertaDTO ofertaActual = subasta.getOfertaActual();
            float valorOfertaActual = ofertaActual.getValor();
            float valorOfertaNueva = oferta.getValor();

            if (valorOfertaNueva > valorOfertaActual) {
                // Actualizar la oferta actual en la subasta
                subasta.setOfertaActual(oferta);
                listaOfertas.add(valorOfertaNueva);
                return true; // Oferta realizada con éxito
            } else {
                System.out.println("La oferta debe ser mayor que la oferta actual");
            }
        } else {
            System.out.println("No existe una subasta abierta actualmente");
        }

        return false; // Oferta rechazada
    }
    
    @Override
    public boolean suscribirUsuario(ControladorCallBackInt referencia) {
        System.out.println("Entrando a Suscribir Usuario...");
        return this.listaReferencias.add(referencia);
    }

    @Override
    public void notificarUsuarios (SubastaDTO objSubasta, UsuarioDTO objCompradorFinal){
        System.out.println("Entrando a notificar Usuarios el cierre de la Subasta...");
        //Aqui se realiza el callback, el servidor invoca un metodo remoto del cliente para notificar un regist
        this.listaReferencias.forEach(obj->{
            try {
                obj.notificarCierreSubasta(objSubasta,objCompradorFinal);
            } catch (RemoteException ex) {
                System.out.println("Error al notificar al usuario");
            }
        }
        ); 
    }

    @Override
    public UsuarioDTO abrirCerrarSubasta(ProductoDTO objProducto) {
        System.out.println("Entrando a abrir Cerrar Subasta...");

        if (this.subastaActual != null && this.subastaActual.getEstado().equals(SubastaDTO.EstadoSubasta.ABIERTA)) {
            // Si ya hay una subasta abierta para el producto, cerrarla
            //si se va a cerrar una subasta y ho hay comparador ->marcar producto como NO_SUBASTANDO
            LoginDTO loginCompradorActual = this.subastaActual.getOfertaActual().getCompradorActual();
            UsuarioDTO Comprador= new UsuarioDTO("CERRADA", "SIN", "COMPARDOR", "", null);
            ProductoDTO objProductoActualSub = this.subastaActual.getProducto();
            if (loginCompradorActual!=null) {
                // Marcar el producto como cerrada
                this.subastaActual.setEstado(SubastaDTO.EstadoSubasta.CERRADA);
                // Marcar el producto como vendido 
                objProductoActualSub.setEstado(ProductoDTO.EstadoSubasta.VENDIDO);

                try {
                    
                    Comprador = objRemotoGestorConsulta.consultarUsuario(loginCompradorActual);
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(SubastasRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                //como no hay comprador marcar producto como NO_SUBASTANDO
                objProductoActualSub.setEstado(ProductoDTO.EstadoSubasta.NO_SUBASTANDO);
                // Marcar el producto como cerrada
                this.subastaActual.setEstado(SubastaDTO.EstadoSubasta.CERRADA);
            }
            //Hacer callback a los usuarios informando el ganador
            facturaDTO factura = new facturaDTO(Comprador.getNombres(),
                                                Comprador.getApellidos(),
                                                Comprador.getCorreo(),
                                                Comprador.getTelefono(),
                                                Comprador.getLogin().getLogin(),
                                                this.subastaActual.getProducto().getCodigo(),
                                                this.subastaActual.getProducto().getNombre(),
                                                this.subastaActual.getProducto().getValorInicialOferta(),
                                                this.subastaActual.getOfertaActual().getValor());
            notificarUsuarios(this.subastaActual,Comprador);
            objRemotoFactura.generarFactura(factura);
            return Comprador;

        }//si no hay subasta o es nueva o esta cerrada, crear la subasta
        // Marcar el producto como "SUBASTANDO"
        objProducto.setEstado(ProductoDTO.EstadoSubasta.SUBASTANDO);
        this.subastaActual = new SubastaDTO(objProducto, new OfertaDTO(null, objProducto.getValorInicialOferta()));
        this.subastaActual.setEstado(SubastaDTO.EstadoSubasta.ABIERTA);
        return new UsuarioDTO("ABIERTA", "SUBASTANDO", "", "", null);
    }

    public LinkedList<Float> obtenerOfertas() {
        return listaOfertas;
    }
    
   
}

