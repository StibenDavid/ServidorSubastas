package sop_corba.FacturaIntPackage;


/**
* sop_corba/FacturaIntPackage/facturaDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from factura.idl
* lunes 3 de julio de 2023 06:05:36 PM COT
*/

abstract public class facturaDTOHelper
{
  private static String  _id = "IDL:sop_corba/FacturaInt/facturaDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.FacturaIntPackage.facturaDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.FacturaIntPackage.facturaDTO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [9];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "nombres",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "apellidos",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "correo",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "telefono",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "login",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "codigoProducto",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "nombreProducto",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[7] = new org.omg.CORBA.StructMember (
            "valorInicialOferta",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[8] = new org.omg.CORBA.StructMember (
            "ValorFinalOferta",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (sop_corba.FacturaIntPackage.facturaDTOHelper.id (), "facturaDTO", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.FacturaIntPackage.facturaDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    sop_corba.FacturaIntPackage.facturaDTO value = new sop_corba.FacturaIntPackage.facturaDTO ();
    value.nombres = istream.read_string ();
    value.apellidos = istream.read_string ();
    value.correo = istream.read_string ();
    value.telefono = istream.read_string ();
    value.login = istream.read_string ();
    value.codigoProducto = istream.read_string ();
    value.nombreProducto = istream.read_string ();
    value.valorInicialOferta = istream.read_float ();
    value.ValorFinalOferta = istream.read_float ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.FacturaIntPackage.facturaDTO value)
  {
    ostream.write_string (value.nombres);
    ostream.write_string (value.apellidos);
    ostream.write_string (value.correo);
    ostream.write_string (value.telefono);
    ostream.write_string (value.login);
    ostream.write_string (value.codigoProducto);
    ostream.write_string (value.nombreProducto);
    ostream.write_float (value.valorInicialOferta);
    ostream.write_float (value.ValorFinalOferta);
  }

}