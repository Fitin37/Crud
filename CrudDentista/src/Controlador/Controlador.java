
package Controlador;

import Modelo.Dentista;
import Vista.dentista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
/**
 *
 * @author rdlfp
 */
public class Controlador implements MouseListener{
    private dentista vista;
    private Dentista modelo;

    
    public Controlador(dentista Vista,Dentista Modelo){
    this.vista=Vista;
    this.modelo=Modelo;
    
    Vista.btnAgregar.addMouseListener(this);
    Vista.btnEliminar.addMouseListener(this);
    Vista.btnActualizar.addMouseListener(this);
    Vista.tbDentista.addMouseListener(this);
    
    Modelo.Mostrar(Vista.tbDentista);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            try {
                System.out.println("Guardando cita...");
                modelo.setNombre_dentista(vista.txtNombre.getText());
                modelo.setEdad_dentista(vista.txtEdad.getText());
                modelo.setPeso_dentista(vista.txtPeso.getText());
                modelo.setCorreo_dentista(vista.txtCorreo.getText());

                modelo.Insertar();
                modelo.Mostrar(vista.tbDentista);  // Refrescar la tabla después de insertar
                JOptionPane.showMessageDialog(null, "Cita registrada con éxito", "Cita registrada", JOptionPane.INFORMATION_MESSAGE);
            
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al registrar cita: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
         if (e.getSource() == vista.btnEliminar) {
            modelo.Eliminar(vista.tbDentista);
            modelo.Mostrar(vista.tbDentista);  // Refrescar la tabla después de eliminar
        }
         
           if (e.getSource() == vista.btnActualizar) {
            try {
                modelo.setPeso_dentista(vista.txtPeso.getText());
                modelo.setEdad_dentista(vista.txtEdad.getText());
                modelo.setCorreo_dentista(vista.txtCorreo.getText());

                modelo.Actualiza(vista.tbDentista);
                modelo.Mostrar(vista.tbDentista);  // Refrescar la tabla después de actualizar
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar la cita", "Error", JOptionPane.WARNING_MESSAGE);
            }
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
