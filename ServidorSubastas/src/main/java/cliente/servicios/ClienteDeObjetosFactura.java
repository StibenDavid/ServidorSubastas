/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.servicios;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import servidor.sevicios.ServidorDeObjetosSubastas;
import servidor.utilidades.UtilidadesConsola;
import sop_corba.FacturaInt;
import sop_corba.FacturaIntHelper;

/**
 *
 * @author ASUS
 */
public class ClienteDeObjetosFactura {

    static FacturaInt objRemotoFactura;
   
    public ClienteDeObjetosFactura() {
        ClienteDeObjetosFactura.main(null);
    }
    public static void main(String[] args) {
        try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            System.out.println("Ingrese la dirección IP donde escucha el n_s");
            vec[1] = UtilidadesConsola.leerCadena();
            vec[2] = "-ORBInitialPort";
            System.out.println("Ingrese el puerto donde escucha el n_s");
            vec[3] = UtilidadesConsola.leerCadena();

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);

            // se obtiene un link al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objFactura";
            objRemotoFactura = FacturaIntHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + objRemotoFactura);
            new ClienteDeObjetosSubastas(objRemotoFactura);      
 
            

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

}
