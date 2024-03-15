package org.pr2.interfaces;
import org.pr2.dominio.*;
/*** Implementa una interfaz texto para la libreta de contactos.*/
public class Interfaz{
    /*** Inicia la interfaz con parámetros.*
     *  @param args puede ser <i>add nombre contacto</i> (p.ej. <i>add Juan* 653421367</i>) para añadir contacto, o <i>show</i>para mostrar todos* los contactos de la libreta. */
    public static void iniciar(String args[]){
        Libreta libreta = new Libreta();
        if (args[0].equals("add")){
            libreta.annadirContacto(args[1], args[2]);
        }
        else if (args[0].equals("rm")){
            libreta.borrarContacto(args[1]);
        }else if (args[0].equals("show")) System.out.println(libreta);
        else if (args[0].equals("hoja")){libreta.generarHojaDeCalculo();
            System.out.println("Hoja de cálculo generada enoutput/contactos.ods");
        }else System.out.println("Opción incorrecta");
    }
}