package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
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
        
        btnInsertarSocio,
        btnModificarSocio,
        btnEliminarSocio,
        btnLimpiarSocio,
        
        btnInsertarPrestamo,
        btnModificarPrestamo,
        btnEliminarPrestamo,
        btnLimpiarPrestamo,
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
        
        this.vista.comboLibros.setModel(new DefaultComboBoxModel(modelo.titulosLibros()));
        this.vista.comboSocios.setModel(new DefaultComboBoxModel(modelo.titulosLibros()));
        long t = Long.parseLong(vista.comboSocios.getSelectedItem().toString());
        String n = modelo.nombreSocio(t);
        this.vista.txtNombreSocioPrestamo.setText(n);
        
        this.vista.comboSocios.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                long t = Long.parseLong(vista.comboSocios.getSelectedItem().toString());
                String n = modelo.nombreSocio(t);
                vista.txtNombreSocioPrestamo.setText(n);
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        switch(AccionMVC.valueOf(e.getActionCommand())){
            case btnInsertarLibro:
                if(vista.txtTituloLibro.getText().equals("") || vista.txtEjemplaresLibro.getText().equals("") || vista.txtEditorialLibro.getText().equals("")
                        || vista.txtPaginasLibro.getText().equals("") || vista.txtAnoEdicionLibro.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
                }else{
                    String titulo = vista.txtTituloLibro.getText();
                    long ejemplares = Long.parseLong(vista.txtEjemplaresLibro.getText());
                    String editorial = vista.txtEditorialLibro.getText();
                    int paginas = Integer.parseInt(vista.txtPaginasLibro.getText());
                    int anyo = Integer.parseInt(vista.txtAnoEdicionLibro.getText());
                    
                    modelo.insertarLibro(titulo, ejemplares, editorial, paginas, anyo);
                    
                    vista.txtTituloLibro.setText("");
                    vista.txtEjemplaresLibro.setText("");
                    vista.txtEditorialLibro.setText("");
                    vista.txtPaginasLibro.setText("");
                    vista.txtAnoEdicionLibro.setText("");
                    
                    actualizarInfo();
                }
                break;
            case btnModificarLibro:
                if(vista.tituloS.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Seleccione un libro primero.");
                }else{
                    if(vista.txtTituloLibro.equals("") || vista.txtEjemplaresLibro.equals("") || vista.txtEditorialLibro.equals("") || vista.txtPaginasLibro.equals("")
                            || vista.txtAnoEdicionLibro.equals("")){
                        JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
                    }else{
                        String tituloS = vista.tituloS.getText();
                        String titulo = vista.txtTituloLibro.getText();
                        long ejemplares = Long.parseLong(vista.txtEjemplaresLibro.getText());
                        String editorial = vista.txtEditorialLibro.getText();
                        int paginas = Integer.parseInt(vista.txtPaginasLibro.getText());
                        int anyo = Integer.parseInt(vista.txtAnoEdicionLibro.getText());
                        
                        modelo.modificarLibro(tituloS, titulo, ejemplares, editorial, paginas, anyo);
                        
                        vista.txtTituloLibro.setText("");
                        vista.txtEjemplaresLibro.setText("");
                        vista.txtEditorialLibro.setText("");
                        vista.txtPaginasLibro.setText("");
                        vista.txtAnoEdicionLibro.setText("");
                        
                        actualizarInfo();
                    }
                }
                break;
            case btnEliminarLibro:
                if(vista.tituloS.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Seleccione un libro primero.");
                }else{
                    String tituloS = vista.tituloS.getText();
                    
                    modelo.eliminarLibro(tituloS);
                    
                    vista.txtTituloLibro.setText("");
                    vista.txtEjemplaresLibro.setText("");
                    vista.txtEditorialLibro.setText("");
                    vista.txtPaginasLibro.setText("");
                    vista.txtAnoEdicionLibro.setText("");
                    
                    actualizarInfo();
                }
                break;
            case btnInsertarSocio:
                if(vista.txtNombreSocio.getText().equals("") || vista.txtApellidosSocio.getText().equals("") || vista.txtEdadSocio.getText().equals("")
                        || vista.txtDireccionSocio.getText().equals("") || vista.txtTelefonoSocio.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
                }else{
                    String nombre = vista.txtNombreSocio.getText();
                    String apellidos = vista.txtApellidosSocio.getText();
                    int edad = Integer.parseInt(vista.txtEdadSocio.getText());
                    String direccion = vista.txtDireccionSocio.getText();
                    int telefono = Integer.parseInt(vista.txtTelefonoSocio.getText());
                    
                    modelo.insertarSocio(nombre, apellidos, edad, direccion, telefono);
                    
                    vista.txtNombreSocio.setText("");
                    vista.txtApellidosSocio.setText("");
                    vista.txtEdadSocio.setText("");
                    vista.txtDireccionSocio.setText("");
                    vista.txtTelefonoSocio.setText("");
                    
                    actualizarInfo();
                }
                break;
            case btnModificarSocio:
                if(vista.telefonoSeg.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Seleccione un socio primero.");
                }else{
                    if(vista.txtNombreSocio.getText().equals("") || vista.txtApellidosSocio.getText().equals("") || vista.txtEdadSocio.getText().equals("")
                            || vista.txtDireccionSocio.getText().equals("") || vista.txtTelefonoSocio.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
                    }else{
                        long telefonoS = Long.parseLong(vista.telefonoSeg.getText());
                        String nombre = vista.txtNombreSocio.getText();
                        String apellidos = vista.txtApellidosSocio.getText();
                        int edad = Integer.parseInt(vista.txtEdadSocio.getText());
                        String direccion = vista.txtDireccionSocio.getText();
                        long telefono = Long.parseLong(vista.txtTelefonoSocio.getText());
                        
                        modelo.modificarSocio(telefonoS, nombre, apellidos, edad, direccion, telefono);
                        
                        vista.txtNombreSocio.setText("");
                        vista.txtApellidosSocio.setText("");
                        vista.txtEdadSocio.setText("");
                        vista.txtDireccionSocio.setText("");
                        vista.txtTelefonoSocio.setText("");
                        
                        actualizarInfo();
                    }
                }
                break;
            case btnEliminarSocio:
                if(vista.telefonoSeg.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Seleccione un socio primero.");
                }else{
                    long telefonoS = Long.parseLong(vista.telefonoSeg.getText());
                    
                    modelo.eliminarSocio(telefonoS);
                    
                    vista.txtNombreSocio.setText("");
                    vista.txtApellidosSocio.setText("");
                    vista.txtEdadSocio.setText("");
                    vista.txtDireccionSocio.setText("");
                    vista.txtTelefonoSocio.setText("");
                    
                    actualizarInfo();
                }
                break;
            case btnInsertarPrestamo:
                if(vista.comboLibros.getSelectedItem().toString().equals("") || vista.comboSocios.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(null, "Cree un libro y socio antes que un préstamo.");
                }else{
                    String titulo = vista.comboLibros.getSelectedItem().toString();
                    long telefono = Long.parseLong(vista.comboSocios.getSelectedItem().toString());
                    
                    modelo.insertarPrestamo(titulo, telefono);
                    
                    vista.comboLibros.setSelectedIndex(0);
                    vista.comboSocios.setSelectedIndex(0);
                    
                    actualizarInfo();
                }
                break;
            case btnModificarPrestamo:
                if(vista.comboLibros.getSelectedItem().toString().equals("") || vista.comboSocios.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(null, "Cree un libro y socio antes que un préstamo.");
                }else{
                    if(vista.tituloS.getText().equals("") || vista.telefonoS.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Seleccione un préstamo primero.");
                    }else{
                        String tituloS = vista.tituloS.getText();
                        long telefonoS = Long.parseLong(vista.telefonoS.getText());
                        String titulo = vista.comboLibros.getSelectedItem().toString();
                        long telefono = Long.parseLong(vista.comboSocios.getSelectedItem().toString());
                        
                        modelo.modificarPrestamo(tituloS, telefonoS, titulo, telefono);
                        
                        vista.comboLibros.setSelectedIndex(0);
                        vista.comboSocios.setSelectedIndex(0);
                    
                        actualizarInfo();
                    }
                }
                
                break;
            case btnEliminarPrestamo:
                if(vista.tituloS.getText().equals("") || vista.telefonoS.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Seleccione un préstamo primero.");
                }else{
                    String tituloS = vista.tituloS.getText();
                    long telefonoS = Long.parseLong(vista.telefonoS.getText());
                    
                    modelo.eliminarPrestamo(tituloS, telefonoS);
                    
                    vista.comboLibros.setSelectedIndex(0);
                    vista.comboSocios.setSelectedIndex(0);
                    
                    actualizarInfo();
                }
                break;
            case btnLimpiarLibro:
                vista.tituloSeg.setText("");
                vista.txtTituloLibro.setText("");
                vista.txtEjemplaresLibro.setText("");
                vista.txtEditorialLibro.setText("");
                vista.txtPaginasLibro.setText("");
                vista.txtAnoEdicionLibro.setText("");
                break;
            case btnLimpiarSocio:
                vista.telefonoSeg.setText("");
                vista.txtNombreSocio.setText("");
                vista.txtApellidosSocio.setText("");
                vista.txtEdadSocio.setText("");
                vista.txtDireccionSocio.setText("");
                vista.txtTelefonoSocio.setText("");
                break;
            case btnLimpiarPrestamo:
                vista.telefonoS.setText("");
                vista.tituloS.setText("");
                vista.comboLibros.setSelectedIndex(0);
                vista.comboSocios.setSelectedIndex(0);
                break;
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()== 1){
            int libro = vista.tablaLibros.rowAtPoint(e.getPoint());
            int socio = vista.tablaSocios.rowAtPoint(e.getPoint());
            int prestamo = vista.tablaPrestamos.rowAtPoint(e.getPoint());
            if (libro > -1){
                String tituloSeg = String.valueOf(vista.tablaLibros.getValueAt(libro, 0));
                String titulo = String.valueOf(vista.tablaLibros.getValueAt(libro, 0));
                String ejemplares = String.valueOf(vista.tablaLibros.getValueAt(libro, 1));
                String editorial = String.valueOf(vista.tablaLibros.getValueAt(libro, 2));
                String paginas = String.valueOf(vista.tablaLibros.getValueAt(libro, 3));
                String anyo = String.valueOf(vista.tablaLibros.getValueAt(libro, 4));
                
                vista.tituloSeg.setText(tituloSeg);
                vista.txtTituloLibro.setText(titulo);
                vista.txtEjemplaresLibro.setText(ejemplares);
                vista.txtEditorialLibro.setText(editorial);
                vista.txtPaginasLibro.setText(paginas);
                vista.txtAnoEdicionLibro.setText(anyo);
            }
            if (socio > -1){                
                String telefonoSeg = String.valueOf(vista.tablaSocios.getValueAt(socio, 4));
                String nombre = String.valueOf(vista.tablaSocios.getValueAt(socio, 0));
                String apellidos = String.valueOf(vista.tablaSocios.getValueAt(socio, 1));
                String edad = String.valueOf(vista.tablaSocios.getValueAt(socio, 2));
                String direccion = String.valueOf(vista.tablaSocios.getValueAt(socio, 3));
                String telefono = String.valueOf(vista.tablaSocios.getValueAt(socio, 4));
                
                vista.telefonoSeg.setText(telefonoSeg);
                vista.txtNombreSocio.setText(nombre);
                vista.txtApellidosSocio.setText(apellidos);
                vista.txtEdadSocio.setText(edad);
                vista.txtDireccionSocio.setText(direccion);
                vista.txtTelefonoSocio.setText(telefono);
            }
            if (prestamo > -1){
                String titulo = String.valueOf(vista.tablaSocios.getValueAt(socio, 0));
                String nombre = String.valueOf(vista.tablaSocios.getValueAt(socio, 1));
                String telefono = String.valueOf(vista.tablaSocios.getValueAt(socio, 2));
                
                vista.tituloS.setText(titulo);
                vista.txtNombreSocioPrestamo.setText(nombre);
                vista.telefonoS.setText(telefono);
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
    
    public void actualizarInfo(){
        vista.tablaLibros.setModel(modelo.getTablaLibros());
        vista.tablaSocios.setModel(modelo.getTablaSocios());
        vista.tablaPrestamos.setModel(modelo.getTablaPrestamos());
        vista.comboLibros.setModel(new DefaultComboBoxModel(modelo.titulosLibros()));
        vista.comboSocios.setModel(new DefaultComboBoxModel(modelo.titulosLibros()));
    }
}