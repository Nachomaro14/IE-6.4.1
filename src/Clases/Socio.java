package Clases;

public class Socio {
    private String nombre;
    private String apellidos;
    private int edad;
    private String direccion;
    private long telefono;
    
    public Socio(String n, String a, int e, String d, long t){
        nombre = n;
        apellidos = a;
        edad = e;
        direccion = d;
        telefono = t;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    
    public String toString(){
        return nombre + " " + apellidos + " (" + edad + " años).\n"
                + direccion + " - " + telefono + ".";
    }
}