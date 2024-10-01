package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rdlfp
 */
public class Dentista {

    private String UUID_dentista;
    private String Nombre_dentista;
    private String Edad_dentista;
    private String Peso_dentista;
    private String Correo_dentista;

    public String getUUID_dentista() {
        return UUID_dentista;
    }

    public void setUUID_dentista(String UUID_dentista) {
        this.UUID_dentista = UUID_dentista;
    }

    public String getNombre_dentista() {
        return Nombre_dentista;
    }

    public void setNombre_dentista(String Nombre_dentista) {
        this.Nombre_dentista = Nombre_dentista;
    }

    public String getEdad_dentista() {
        return Edad_dentista;
    }

    public void setEdad_dentista(String Edad_dentista) {
        this.Edad_dentista = Edad_dentista;
    }

    public String getPeso_dentista() {
        return Peso_dentista;
    }

    public void setPeso_dentista(String Peso_dentista) {
        this.Peso_dentista = Peso_dentista;
    }

    public String getCorreo_dentista() {
        return Correo_dentista;
    }

    public void setCorreo_dentista(String Correo_dentista) {
        this.Correo_dentista = Correo_dentista;
    }

    //Insercion de datos
    public void Insertar() {
        Connection conexion = Conexion.getConexion();
        try {
            PreparedStatement addCita = conexion.prepareStatement("insert into tbDentista(UUID_dentista,Nombre_dentista,Edad_dentista,Peso_dentista,Correo_dentista) values(?,?,?,?,?)");
            addCita.setString(1, UUID.randomUUID().toString());
            addCita.setString(2, getNombre_dentista());
            addCita.setString(3, getEdad_dentista());
            addCita.setNString(4, getPeso_dentista());
            addCita.setString(5, getCorreo_dentista());
            addCita.execute();
        } catch (SQLException ex) {
            System.out.println("este es el error" + ex);
        }
    }

    //Mostrar datos
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = Conexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_dentista", "Nombre_dentista", "Edad_dentista", "Peso_dentista", "Correo_dentista"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM tbDentista";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_dentista"),
                    rs.getString("Nombre_dentista"),
                    rs.getString("Edad_dentista"),
                    rs.getString("Peso_dentista"),
                    rs.getString("Correo_dentista")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);

            //tabla.getColumnModel().getColumn(0).setMinWidth(0);
            //tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            //tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    //Eliminaciond de datos
    public void Eliminar(JTable tabla) {
        Connection conexion = Conexion.getConexion();

        int filaSelecionada = tabla.getSelectedRow();

        if (filaSelecionada != -1) {

            String miID = tabla.getValueAt(filaSelecionada, 0).toString();
            try {
                String sql = "DELETE FROM tbDentista WHERE UUID_dentista = ?";
                PreparedStatement eliminarCita = conexion.prepareStatement(sql);
                eliminarCita.setString(1, miID);

                eliminarCita.executeUpdate();

                System.out.println("cita Eliminada Correctamente.");

                Mostrar(tabla);

            } catch (SQLException e) {
                System.out.println("error al eliminar Cita" + e);
            }
        } else {
            System.out.println("no se econtro nada");
        }
    }

    //Actualizacion de datos
    public void Actualiza(JTable tabla) {
        Connection conexion = Conexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "UPDATE tbDentista SET Peso_dentista = ?, Edad_dentista = ?, Correo_dentista = ? WHERE UUID_dentista = ?";
                PreparedStatement actualizarCita = conexion.prepareStatement(sql);

                actualizarCita.setString(1, getPeso_dentista());
                actualizarCita.setString(2, getEdad_dentista());
                actualizarCita.setString(3, getCorreo_dentista());
                actualizarCita.setString(4, miUUId);

                actualizarCita.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Este es el error en el método de actualizar: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
}
