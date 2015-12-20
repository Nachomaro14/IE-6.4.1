package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import clases.*;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Modelo{
    
    ObjectContainer bd;
    
    public Modelo(){
    
    }
    
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column){  
            return false;  
        }
    }
    
    public DefaultTableModel getTablaLibros(){
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"Título","Ejemplares","Editorial", "Páginas", "Año de Edicion"};
        
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Libro.class);
            ObjectSet result = query.execute();
            while(result.hasNext()){
                registros = result.size();
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al contar tuplas\n" + e.getMessage());
            e.printStackTrace();
        }
        Object[][] data = new String[registros][5];
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Libro.class);
            ObjectSet result = query.execute();
            int i = 0;
            while(result.hasNext()){
                Libro l = (Libro) result.next();
                data[i][0] = l.getTitulo();
                data[i][1] = l.getEjemplares();
                data[i][2] = l.getEditorial();
                data[i][3] = l.getPaginas();
                data[i][4] = l.getAnyo();
                i++;
            }
            bd.close();
            tablemodel.setDataVector(data, columNames);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel getTablaSocios(){
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"Nombre","Apellidos","Edad", "Dirección", "Teléfono"};
      
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            ObjectSet result = query.execute();
            while(result.hasNext()){
                registros = result.size();
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al contar tuplas\n" + e.getMessage());
            e.printStackTrace();
        }
        Object[][] data = new String[registros][5];
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            ObjectSet result = query.execute();
            int i = 0;
            while(result.hasNext()){
                Socio s = (Socio) result.next();
                data[i][0] = s.getNombre();
                data[i][1] = s.getApellidos();
                data[i][2] = s.getEdad();
                data[i][3] = s.getDireccion();
                data[i][4] = s.getTelefono();
                i++;
            }
            bd.close();
            tablemodel.setDataVector(data, columNames);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel getTablaPrestamos(){
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"Título","Nombre de Socio","Teléfono de Socio"};
      
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Prestamo.class);
            ObjectSet result = query.execute();
            while(result.hasNext()){
                registros = result.size();
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al contar tuplas\n" + e.getMessage());
            e.printStackTrace();
        }
        Object[][] data = new String[registros][3];
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Prestamo.class);
            ObjectSet result = query.execute();
            int i = 0;
            while(result.hasNext()){
                Prestamo p = (Prestamo) result.next();
                data[i][0] = p.getLibro();
                data[i][1] = p.getSocio().getNombre() + " " + p.getSocio().getApellidos();
                data[i][2] = p.getSocio().getTelefono();
                i++;
            }
            bd.close();
            tablemodel.setDataVector(data, columNames);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public boolean insertarLibro(String titulo, long ejemplares, String editorial, int paginas, int anyo){
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Libro l = new Libro(titulo, ejemplares, editorial, paginas, anyo);
            bd.store(l);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al introducir nuevo libro\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean modificarLibro(String tituloS, String titulo, int ejemplares, String editorial, int paginas, int anyo){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Libro.class);
            query.descend("titulo").constrain(tituloS);
            ObjectSet result = query.execute();
            Libro l = (Libro) result.next();
            l.setTitulo(titulo);
            l.setEjemplares(ejemplares);
            l.setEditorial(editorial);
            l.setPaginas(paginas);
            l.setAnyo(anyo);
            bd.store(l);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al modificar el libro\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarLibro(String tituloS){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Libro.class);
            query.descend("titulo").constrain(tituloS);
            ObjectSet result = query.execute();
            Libro l = (Libro) result.next();
            bd.delete(l);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar el libro\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean insertarSocio(String nombre, String apellidos, int edad, String direccion, long telefono){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Socio s = new Socio(nombre, apellidos, edad, direccion, telefono);
            bd.store(s);
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al introducir nuevo socio\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean modificarSocio(long telefonoS, String nombre, String apellidos, int edad, String direccion, int telefono){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefonoS);
            ObjectSet result = query.execute();
            Socio s = (Socio) result.next();
            s.setNombre(nombre);
            s.setApellidos(apellidos);
            s.setEdad(edad);
            s.setDireccion(direccion);
            s.setTelefono(telefono);
            bd.store(s);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al modificar el socio\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarSocio(long telefonoS){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefonoS);
            ObjectSet result = query.execute();
            Socio s = (Socio) result.next();
            bd.delete(s);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar el socio\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean insertarPrestamo(String titulo, long telefono){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefono);
            ObjectSet result = query.execute();
            Socio s = (Socio) result.next();
            bd.close();
            
            bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH) + 1;
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            String fi = "" + dia + "/" + mes + "/" + año;
            int mesf = mes + 3;
            String ff = "" + dia + "/" + mesf + "/" + año;
            
            Prestamo p = new Prestamo(titulo, s, fi, ff);
            bd.store(p);
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al introducir nuevo préstamo\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean modificarPrestamo(String tituloS, long telefonoS, String titulo, long telefono){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefonoS);
            ObjectSet result = query.execute();
            Socio sS = (Socio) result.next();
            bd.close();
            
            bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefono);
            result = query.execute();
            Socio s = (Socio) result.next();
            bd.close();
            
            bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            query = bd.query();
            query.constrain(Prestamo.class);
            query.descend("libro").constrain(tituloS);
            query.descend("socio").constrain(sS);
            result = query.execute();
            Prestamo p = (Prestamo) result.next();
            p.setLibro(titulo);
            p.setSocio(s);
            bd.store(p);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al modificar el préstamo\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarPrestamo(String tituloS, long telefonoS){
        try {
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefonoS);
            ObjectSet result = query.execute();
            Socio sS = (Socio) result.next();
            bd.close();
            
            bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            query = bd.query();
            query.constrain(Prestamo.class);
            query.descend("libro").constrain(tituloS);
            query.descend("socio").constrain(sS);
            result = query.execute();
            Prestamo p = (Prestamo) result.next();
            bd.delete(p);
            bd.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar el préstamo\n" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public String[] titulosLibros(){
        int num = 0;
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Libro.class);
            ObjectSet result = query.execute();
            while(result.hasNext()){
                num = result.size();
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener número de títulos\n" + e.getMessage());
            e.printStackTrace();
        }
        String[] titulos = new String[num];
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Libro.class);
            ObjectSet result = query.execute();
            int i = 0;
            while(result.hasNext()){
                Libro l = (Libro) result.next();
                titulos[i] = l.getTitulo();
                i++;
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener títulos\n" + e.getMessage());
            e.printStackTrace();
        }
        return titulos;
    }
    
    public long[] telefonosSocios(){
        int num = 0;
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            ObjectSet result = query.execute();
            while(result.hasNext()){
                num = result.size();
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener número de teléfonos\n" + e.getMessage());
            e.printStackTrace();
        }
        long[] telefonos = new long[num];
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            ObjectSet result = query.execute();
            int i = 0;
            while(result.hasNext()){
                Socio s = (Socio) result.next();
                telefonos[i] = s.getTelefono();
                i++;
            }
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener teléfonos\n" + e.getMessage());
            e.printStackTrace();
        }
        return telefonos;
    }
    
    public String nombreSocio(long telefono){
        String nombre = "";
        try{
            ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "libreria.db4o");
            Query query = bd.query();
            query.constrain(Socio.class);
            query.descend("telefono").constrain(telefono);
            ObjectSet result = query.execute();
            Socio s = (Socio) result.next();
            nombre = s.getNombre() + " " + s.getApellidos();
            bd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener nombre\n" + e.getMessage());
            e.printStackTrace();
        }
        return nombre;
    }
}