package Clases;

public class Libro {
    private String titulo;
    private long ejemplares;
    private String editorial;
    private int paginas;
    private int anyo;
    
    public Libro(String t, long ej, String ed, int p, int a){
        titulo = t;
        ejemplares = ej;
        editorial = ed;
        paginas = p;
        anyo = a;
    }

    public String getTitulo() {
        return titulo;
    }

    public long getEjemplares() {
        return ejemplares;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEjemplares(long ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
    
    public String toString(){
        return titulo + " - " + editorial + " (" + anyo + ").\n"
                + paginas + " páginas.\n"
                + "(" + ejemplares + " ejemplares).";
    }
}