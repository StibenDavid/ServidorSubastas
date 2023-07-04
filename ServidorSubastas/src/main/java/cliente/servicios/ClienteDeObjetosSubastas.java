package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;

import servidor.controladores.ControladorGestorConsultaInt;
import servidor.sevicios.ServidorDeObjetosSubastas;
import sop_corba.FacturaInt;

public class ClienteDeObjetosSubastas
{

    private static ControladorGestorConsultaInt objRemotoGestorConsulta;
    private static FacturaInt objRemotoFactura=null;

    public ClienteDeObjetosSubastas(FacturaInt objRemotoFactura ) throws RemoteException {
        this.objRemotoFactura = objRemotoFactura;
        ClienteDeObjetosSubastas.main(null);
        
    }
    
    public static void main(String args[]) throws RemoteException
    {
        System.out.println("===iniciando segundo el ClienteDeObjetosSubastas==="); 
        int numPuertoRMIRegistryConsulta = 0;       
        String direccionIpRMIRegistry = "";        

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry para Servidor GestionarConsulta");
        numPuertoRMIRegistryConsulta = cliente.utilidades.UtilidadesConsola.leerEntero();  
        
        objRemotoGestorConsulta = (ControladorGestorConsultaInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistryConsulta, "objRemotoGestorConsulta");
        new ServidorDeObjetosSubastas(objRemotoGestorConsulta,objRemotoFactura);
        

    }
	
}

