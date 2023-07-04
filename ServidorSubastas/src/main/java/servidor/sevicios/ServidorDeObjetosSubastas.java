/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.sevicios;


import cliente.servicios.ClienteDeObjetosFactura;
import cliente.servicios.ClienteDeObjetosSubastas;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestorConsultaInt;
import servidor.controladores.ControladorGestorProductosImpl;
import servidor.controladores.ControladorGestorSubastasImpl;
import servidor.repositorios.ProductosRepository;
import servidor.repositorios.SubastasRepository;
import sop_corba.FacturaInt;
//import servidor.Repositorios.UsuariosRepository;
//import servidor.controladores.ControladorGestorAdministradoresImpl;
//import servidor.controladores.ControladorGestorUsuariosImpl;

public class ServidorDeObjetosSubastas
{
    private static ControladorGestorConsultaInt objRemotoGestorConsulta=null;
    private static FacturaInt objRemotoFactura=null;
    
    public ServidorDeObjetosSubastas(ControladorGestorConsultaInt objRemotoGestorConsulta, FacturaInt objRemotoFactura1) throws RemoteException {
        this.objRemotoGestorConsulta = objRemotoGestorConsulta;
        this.objRemotoFactura = objRemotoFactura;
        ServidorDeObjetosSubastas.main(null);
    }

    public static void main(String args[]) throws RemoteException
    {        
        if(objRemotoGestorConsulta==null && objRemotoFactura==null ){
            System.out.println("===iniciando primero el ClienteDeObjetosFactura===");
            new ClienteDeObjetosFactura();
            //new ClienteDeObjetosSubastas();
            return;
        }
        int numPuertoRMIRegistry = 0;

        String direccionIpRMIRegistry = "";

        System.out.println("===iniciando tercero el ServidorDeObjetosSubastas===");               
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry "); 
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
        ProductosRepository objProductosRepository = new ProductosRepository();  
        SubastasRepository objSubastasRepository = new SubastasRepository(objRemotoGestorConsulta,objRemotoFactura);
        ServidorDeObjetosOfertas objServidorOfertas = new ServidorDeObjetosOfertas(objSubastasRepository);
        
        
        //UsuariosRepository objRepository = new UsuariosRepository();
        ControladorGestorProductosImpl objRemotoGestionProductos = new ControladorGestorProductosImpl(objProductosRepository, objSubastasRepository);
        ControladorGestorSubastasImpl objRemotoGestionSubastas = new ControladorGestorSubastasImpl(objSubastasRepository, objProductosRepository);
        ///ControladorGestorAdministradoresImpl objRemotoGestionAdministradores = new ControladorGestorAdministradoresImpl();
        //ControladorGestorUsuariosImpl objRemotoGestionUsuarios = new ControladorGestorUsuariosImpl(objRepository,objRemotoGestionAdministradores);//se leasigna el puerto de escucha del objeto remoto
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionProductos, direccionIpRMIRegistry, numPuertoRMIRegistry, "objRemotoGestionProductos");
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionSubastas, direccionIpRMIRegistry, numPuertoRMIRegistry, "objRemotoGestionSubastas");

           //UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionUsuarios, direccionIpRMIRegistry, numPuertoRMIRegistry, "objRemotoGestorUsuarios");
           //UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionAdministradores, direccionIpRMIRegistry, numPuertoRMIRegistry, "objRemotoGestorAdministradores");
           
        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
}
