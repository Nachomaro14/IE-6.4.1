package Clases;

import java.util.ArrayList;

public class Prestamo {
    ArrayList<Libro> libros;
    private Socio socio;
    private String fechaI;
    private String fechaF;
    
    public Prestamo(ArrayList<Libro> l, Socio s, String fi, String ff){
        libros = l;
        socio = s;
        fechaI = fi;
        fechaF = ff;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public Socio getSocio() {
        return socio;
    }

    public String getFechaI() {
        return fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
    
    public String toString(){
        String s = socio.toString() + "\n\n";
        for(int i = 0; i < libros.size() - 1; i++){
            s = s + libros.get(i).toString() + "\n";
        }
        s = s + "\n"
                + "(" + fechaI + " - " + fechaF + ")";
        return s;
    }
}