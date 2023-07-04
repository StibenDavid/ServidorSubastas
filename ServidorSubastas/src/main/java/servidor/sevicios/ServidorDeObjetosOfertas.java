/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.sevicios;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import servidor.repositorios.SubastasRepository;
import servidorOfertas.ObtenerOfertasImpl;
import sop_corba.OfertasInt;
import sop_corba.OfertasIntHelper;
import servidor.utilidades.UtilidadesConsola;
import sop_corba.OfertasIntHelper;

/**
 *
 * @author ASUS
 */
public class ServidorDeObjetosOfertas {
    
     private static SubastasRepository subastasRepository=null;

    public ServidorDeObjetosOfertas(SubastasRepository subastasRepository) {
        this.subastasRepository = subastasRepository;
        ServidorDeObjetosOfertas.main(null);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
        String[] vec = new String[4];
        vec[0] = "-ORBInitialHost";
        System.out.println("Ingrese la dirección IP donde escucha el n_s");
        vec[1] = UtilidadesConsola.leerCadena();
        vec[2] = "-ORBInitialPort";
        System.out.println("Ingrese el puerto donde escucha el n_s");
        vec[3] = UtilidadesConsola.leerCadena();
        ObtenerOfertasImpl objRemoto = new ObtenerOfertasImpl(subastasRepository);
        inicializarORB(vec,objRemoto);
    } 
	
	catch (Exception e) {
		System.out.println("Error: " + e);
		e.printStackTrace(System.out);
	}

	System.out.println("Servidor: Saliendo ...");
    }
    
    private static void inicializarORB(String[] vec, ObtenerOfertasImpl objRemoto) throws ServantNotActive, WrongPolicy, org.omg.CORBA.ORBPackage.InvalidName, AdapterInactive, InvalidName, NotFound, CannotProceed
  {             
        
      // crea e inicia el ORB
      ORB orb = ORB.init(vec, null);      
      POA rootpoa =  POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
       
      //*** se genera la referencia del servant
      org.omg.CORBA.Object obj = rootpoa.servant_to_reference(objRemoto);
      OfertasInt href = OfertasIntHelper.narrow(obj);
	  
      // se obtiene un link al name service
      org.omg.CORBA.Object objref =orb.resolve_initial_references("NameService");
      NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

      // *** se realiza el binding de la referencia del servant en el N_S ***
      String name = "objOfertas";
      NameComponent path[] = ncref.to_name( name );
      ncref.rebind(path, href);

      System.out.println("El Servidor esta listo y esperando ...");

      // esperan por las invocaciones desde los clientes
      orb.run();
  }
    
}