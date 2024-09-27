package modelo;

import java.util.UUID;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmMecanicos;


public class Mecanico {
    //1-Parametros
    String UUID_mecanico;
    String nombre;
    int edad;
    double peso;
    String correo;
    
    //2- Getters y Setters

    public String getUUID_mecanico() {
        return UUID_mecanico;
    }

    public void setUUID_mecanico(String UUID_mecanico) {
        this.UUID_mecanico = UUID_mecanico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    //3-Funciones
      
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addMecanico = conexion.prepareStatement("INSERT INTO tbMecanico(UUID_Mecanico, nombre_mecanico, edad_mecanico, peso_mecanico, correo_mecanico) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addMecanico.setString(1, UUID.randomUUID().toString());
            addMecanico.setString(2, getNombre());
            addMecanico.setInt(3, getEdad());
            addMecanico.setDouble(4, getPeso());
            addMecanico.setString(5, getCorreo());
            
 
            //Ejecutar la consulta
            addMecanico.executeUpdate();
            
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloMecanico = new DefaultTableModel();
        modeloMecanico.setColumnIdentifiers(new Object[]{"Nombre", "Edad", "Peso", "Correo electrónico"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbMecanico");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloMecanico.addRow(new Object[]{rs.getString("nombre_mecanico"), 
                    rs.getInt("edad_mecanico"), 
                    rs.getDouble("peso_mecanico"), 
                    rs.getString("correo_mecanico")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloMecanico);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteMecanico = conexion.prepareStatement("delete from tbMecanico where UUID_mecanico = ?");
            deleteMecanico.setString(1, miId);
            deleteMecanico.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
         public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbMecanico set nombre_mecanico= ?, edad_mecanico = ?, peso_mecanico = ?, correo_mecanico where UUID_mecanico = ?");
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no funciona actualizar");
        }
    }
         
           public void cargarDatosTabla(frmMecanicos vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbProducto.getSelectedRow();
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbProducto.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbProducto.getValueAt(filaSeleccionada, 1).toString();
            String PrecioTB = vista.jtbProducto.getValueAt(filaSeleccionada, 2).toString();
            String CategoriaTB = vista.jtbProducto.getValueAt(filaSeleccionada, 3).toString();
            
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtCorreo.setText(PrecioTB);
            vista.txtEdad.setText(CategoriaTB);
        }
    }
}
