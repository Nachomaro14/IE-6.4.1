package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import vista.Interfaz;

public class Controlador implements ActionListener, MouseListener{
    
    Interfaz vista;
    Modelo modelo = new Modelo();
    
    public Controlador(Interfaz v){
        vista = v;
    }

    public enum AccionMVC{
        btnInsertarLibro,
        btnModificarLibro,
        btnEliminarLibro,
        btnLimpiarLibro,
        iTLibros,
        
        btnInsertarSocio,
        btnModificarSocio,
        btnEliminarSocio,
        btnLimpiarSocio,
        iTSocios,
        
        btnInsertarPrestamo,
        btnModificarPrestamo,
        btnEliminarPrestamo,
        btnLimpiarPrestamo,
        iTPrestamos,
        
        btnConsultarNombre,
        btnConsultarApellido,
        btnConsultarTitulo,
        btnLibrosFuera,
        btnClientesMorosos
    }
    
    public void iniciar(){
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.pack();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex){}
          catch (ClassNotFoundException ex){}
          catch (InstantiationException ex){}
          catch (IllegalAccessException ex){}
        
        this.vista.btnInsertarLibro.setActionCommand("btnInsertarLibro");
        this.vista.btnInsertarLibro.addActionListener(this);
        this.vista.btnModificarLibro.setActionCommand("btnModificarLibro");
        this.vista.btnModificarLibro.addActionListener(this);
        this.vista.btnEliminarLibro.setActionCommand("btnEliminarLibro");
        this.vista.btnEliminarLibro.addActionListener(this);
        this.vista.btnLimpiarLibro.setActionCommand("btnLimpiarLibro");
        this.vista.btnLimpiarLibro.addActionListener(this);
        
        this.vista.btnInsertarSocio.setActionCommand("btnInsertarSocio");
        this.vista.btnInsertarSocio.addActionListener(this);
        this.vista.btnModificarSocio.setActionCommand("btnModificarSocio");
        this.vista.btnModificarSocio.addActionListener(this);
        this.vista.btnEliminarSocio.setActionCommand("btnEliminarSocio");
        this.vista.btnEliminarSocio.addActionListener(this);
        this.vista.btnLimpiarSocio.setActionCommand("btnLimpiarSocio");
        this.vista.btnLimpiarSocio.addActionListener(this);
        
        this.vista.btnInsertarPrestamo.setActionCommand("btnInsertarPrestamo");
        this.vista.btnInsertarPrestamo.addActionListener(this);
        this.vista.btnModificarPrestamo.setActionCommand("btnModificarPrestamo");
        this.vista.btnModificarPrestamo.addActionListener(this);
        this.vista.btnEliminarPrestamo.setActionCommand("btnEliminarPrestamo");
        this.vista.btnEliminarPrestamo.addActionListener(this);
        this.vista.btnLimpiarPrestamo.setActionCommand("btnLimpiarPrestamo");
        this.vista.btnLimpiarPrestamo.addActionListener(this);
        
        this.vista.tablaLibros.addMouseListener(this);
        this.vista.tablaLibros.setModel(modelo.getTablaLibros());
        this.vista.tablaLibros.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaLibros.getTableHeader().setResizingAllowed(false);
        
        this.vista.tablaSocios.addMouseListener(this);
        this.vista.tablaSocios.setModel(modelo.getTablaSocios());
        this.vista.tablaSocios.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaSocios.getTableHeader().setResizingAllowed(false);
        
        this.vista.tablaPrestamos.addMouseListener(this);
        this.vista.tablaPrestamos.setModel(modelo.getTablaPrestamos());
        this.vista.tablaPrestamos.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaPrestamos.getTableHeader().setResizingAllowed(false);
        
        DefaultTableModel modeloTablaDefault = new DefaultTableModel();
    }
    
    public void actionPerformed(ActionEvent e) {
        switch(AccionMVC.valueOf(e.getActionCommand())){
            case btnInsertarLibro:
                if(vista.txtTituloLibro.getText().equals("") || vista.txtEjemplaresLibro.getText().equals("") || vista.txtEditorialLibro.getText().equals("")
                        || vista.txtPaginasLibro.getText().equals("") || vista.txtAnoEdicionLibro.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Rellene todos los campos");
                }else{
                    String titulo = vista.txtTituloLibro.getText();
                    long ejemplares = Long.parseLong(vista.txtEjemplaresLibro.getText());
                    String editorial = vista.txtEditorialLibro.getText();
                    int paginas = Integer.parseInt(vista.txtPaginasLibro.getText());
                    int anyo = Integer.parseInt(vista.txtAnoEdicionLibro.getText());
                    
                    modelo.insertarLibro(titulo, ejemplares, editorial, paginas, anyo);
                    this.vista.tablaLibros.setModel(modelo.getTablaLibros());
                    
                    vista.txtTituloLibro.setText("");
                    vista.txtEjemplaresLibro.setText("");
                    vista.txtEditorialLibro.setText("");
                    vista.txtPaginasLibro.setText("");
                    vista.txtAnoEdicionLibro.setText("");
                }
                break;
            case btnModificarLibro:
                
                break;
            case btnEliminarLibro:
                
                break;
            case btnInsertarSocio:
                if(vista.txtNombreSocio.getText().equals("") || vista.txtApellidosSocio.getText().equals("") || vista.txtEdadSocio.getText().equals("")
                        || vista.txtDireccionSocio.getText().equals("") || vista.txtTelefonoSocio.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Rellene todos los campos");
                }else{
                    String nombre = vista.txtNombreSocio.getText();
                    String apellidos = vista.txtApellidosSocio.getText();
                    int edad = Integer.parseInt(vista.txtEdadSocio.getText());
                    String direccion = vista.txtDireccionSocio.getText();
                    int telefono = Integer.parseInt(vista.txtTelefonoSocio.getText());
                    
                    modelo.insertarSocio(nombre, apellidos, edad, direccion, telefono);
                    this.vista.tablaSocios.setModel(modelo.getTablaSocios());
                    
                    vista.txtNombreSocio.setText("");
                    vista.txtApellidosSocio.setText("");
                    vista.txtEdadSocio.setText("");
                    vista.txtDireccionSocio.setText("");
                    vista.txtTelefonoSocio.setText("");
                }
                break;
            case btnModificarSocio:
                
                break;
            case btnEliminarSocio:
                
                break;
            case btnInsertarPrestamo:
                
                break;
            case btnModificarPrestamo:
                
                break;
            case btnEliminarPrestamo:
                
                break;
            case btnLimpiarLibro:
                vista.txtTituloLibro.setText("");
                vista.txtEjemplaresLibro.setText("");
                vista.txtEditorialLibro.setText("");
                vista.txtPaginasLibro.setText("");
                vista.txtAnoEdicionLibro.setText("");
                break;
            case btnLimpiarSocio:
                vista.txtNombreSocio.setText("");
                vista.txtApellidosSocio.setText("");
                vista.txtEdadSocio.setText("");
                vista.txtDireccionSocio.setText("");
                vista.txtTelefonoSocio.setText("");
                break;
            case btnLimpiarPrestamo:
                
                break;
            case btnConsultarNombre:
                
                break;
            case btnConsultarApellido:
                
                break;
            case btnConsultarTitulo:
                
                break;
            case iTLibros:
                
                break;
            case iTSocios:
                
                break;
            case iTPrestamos:
                
                break;
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()== 1){
            int libro = vista.tablaLibros.rowAtPoint(e.getPoint());
            int socio = vista.tablaSocios.rowAtPoint(e.getPoint());
            int prestamo = vista.tablaPrestamos.rowAtPoint(e.getPoint());
            if (libro > -1){
                
            }
            if (socio > -1){                
                
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}