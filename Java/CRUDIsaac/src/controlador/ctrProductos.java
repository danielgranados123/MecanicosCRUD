package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Producto;
import vista.frmProductos;


public class ctrProductos implements MouseListener{
    
    //Llamar a las otras capas (modelo, vista)
    private frmProductos vista;
    private Producto modelo;
    
    //Crear el constructor de la clase
    public ctrProductos(frmProductos Vista, Producto Modelo){
        this.vista = Vista;
        this.modelo = Modelo;
        
        vista.btnGuardar.addMouseListener(this);
        
        vista.jtbProducto.addMouseListener(this);
        
        //Para mostrar los datos
        modelo.Mostrar(vista.jtbProducto);
        
        vista.btnEliminar.addMouseListener(this);
        
        vista.btnActualizar.addMouseListener(this);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnGuardar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            modelo.setCategoria(vista.txtCategoria.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbProducto);
            
            vista.txtNombre.setText("");
            vista.txtPrecio.setText("");
            vista.txtCategoria.setText("");
            
        }
        
        if(e.getSource() == vista.btnEliminar){            
            modelo.Eliminar(vista.jtbProducto);
            modelo.Mostrar(vista.jtbProducto);
            
            vista.txtNombre.setText("");
            vista.txtPrecio.setText("");
            vista.txtCategoria.setText("");
            
        }
        
        if (e.getSource() == vista.jtbProducto) {
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){            
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            modelo.setCategoria(vista.txtCategoria.getText());
            
            vista.txtNombre.setText("");
            vista.txtPrecio.setText("");
            vista.txtCategoria.setText("");
            
            modelo.Actualizar(vista.jtbProducto);
            modelo.Mostrar(vista.jtbProducto);
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
