package org.pr2.dominio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
/*** Clase responsable de mantener la libreta de contactos.*/
public class Libreta{
    private String nombreFichero = "contactos.txt";
    private ArrayList<Contacto> contactos = new ArrayList<>();
    /*** Genera una cadena de caracteres a partir de lalibreta.* @return la cadena de caracteres.*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Contacto contacto : contactos){
            sb.append(contacto + "\n");
        }
        return sb.toString();
    }/*** Lee la libreta con sus contactos del fichero <i>contactos.txt</i>.*/
    public Libreta() {
        try
        {
            File fichero = new File(nombreFichero);
            fichero.createNewFile();Scanner sc = new Scanner(fichero);
            while(sc.hasNext()){
                Contacto contacto = newContacto();
                contacto.setNombre(sc.nextLine());
    contacto.setTelefono(sc.nextLine());
    contactos.add(contacto);
}
}catch(IOException ex){
    System.err.println(ex);
}
}
private void volcarContactos(){
    System.out.println(contactos);
    try{
        FileWriter fw = new FileWriter(nombreFichero);
        for(Contacto contacto : contactos){
            fw.write(contacto.getNombre()+"\n");
            fw.write(contacto.getTelefono()+"\n");
        }
        fw.close();
    }catch(IOException ex){System.err.println(ex);
}
}/*** Añade el <i>contacto</i> a la libreta.*/
public void annadirContacto(String nombre, String telefono){
    Contacto contacto = new Contacto(nombre,telefono);
    contactos.add(contacto);this.volcarContactos();
}/*** Borra el contacto cuyo nombre es el indicado en elparámetro** @param nombre del contacto a borrar.*/
public void borrarContacto(String nombre){
    Contacto contacto = new Contacto();
    contacto.setNombre(nombre);
    contactos.remove(contacto);
    this.volcarContactos();
}
/*** Genera una hoja de cálculo*/
public void generarHojaDeCalculo(){
    final Object[][] datos = new Object[contactos.size()][2];
    int i = 0;
    for(Contacto contacto : contactos){
        datos[i][0] = contacto.getNombre();
        datos[i++][1] = contacto.getTelefono();
    }
    String[] columnas = new String[] 
    {
         "Nombre", "Teléfono" 
        };
        TableModel modelo = new DefaultTableModel(datos,columnas);
        final File file = new File("output/contactos.ods");
        try{SpreadSheet.createEmpty(modelo).saveAs(file);
        }catch(IOException ex){System.out.println(ex);
        }
    }
}