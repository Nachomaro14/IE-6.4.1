package clases;

public class Prestamo {
    private String libro;
    private Socio socio;
    private String fechaI;
    private String fechaF;
    
    public Prestamo(String l, Socio s, String fi, String ff){
        libro = l;
        socio = s;
        fechaI = fi;
        fechaF = ff;
    }

    public String getLibro() {
        return libro;
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

    public void setLibro(String libro) {
        this.libro = libro;
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
        s = s + libro + "\n"
                + "(" + fechaI + " - " + fechaF + ")";
        return s;
    }
}