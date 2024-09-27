package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.Mecanico;
import vista.frmMecanicos;


public class ctrlMecanicos implements MouseListener{
    
    //Llamar a las otras capas (modelo, vista)
    private frmMecanicos vista;
    private Mecanico modelo;
    
    //Crear el constructor de la clase
    public ctrlMecanicos(frmMecanicos Vista, Mecanico Modelo){
        this.vista = Vista;
        this.modelo = Modelo;
        
        vista.btnGuardar.addMouseListener(this);
        
        vista.jtbProducto.addMouseListener(this);
        
        //Para mostrar los datos
        modelo.Mostrar(vista.jtbProducto);
        
        vista.btnEliminar.addMouseListener(this);
        
        vista.btnActualizar.addMouseListener(this);
        
        vista.btnLimpiar.addMouseListener(this);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnGuardar){
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbProducto);
            
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            }  
        }
        
        if(e.getSource() == vista.btnEliminar){   
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            modelo.Eliminar(vista.jtbProducto);
            modelo.Mostrar(vista.jtbProducto);
            
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            }
        }
        
        if (e.getSource() == vista.jtbProducto) {
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){       
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
            
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            
            modelo.Actualizar(vista.jtbProducto);
            modelo.Mostrar(vista.jtbProducto);
            }
        }
        
        if(e.getSource() == vista.btnLimpiar){            
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}
