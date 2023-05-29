package controlador;

import modelo.modelo.dao.Usuario;
import modelo.modelo.dao.UsuarioDAO;
import vista.app5.App5;

import javax.swing.*;
import java.sql.SQLException;

public class Controlador {
    private UsuarioDAO dao;
    private App5 vista;

    public Controlador(UsuarioDAO dao, App5 vista) {
        this.dao = dao;
        this.vista = vista;
        iniciarApp();
        registrarEventosBoton();
    }

    private void registrarEventosBoton() {
        vista.getBotonBorrar().addActionListener(actionEvent -> borrarFila());
        vista.getBotonActualizar().addActionListener(actionEvent -> actualizarFila());
    }

    private void actualizarFila() {
        JOptionPane.showMessageDialog(null, "Cada vez que cambias ", "alert", JOptionPane.ERROR_MESSAGE);
        int row = vista.getTabla().getSelectedRow();
        //System.out.println("fila: " + row);
        String sID = ((ModeloTabla) vista.getTabla().
                getModel()).getData().get(row)[0];
        String nombre = ((ModeloTabla) vista.getTabla().
                getModel()).getData().get(row)[1];
        String dni = ((ModeloTabla) vista.getTabla().
                getModel()).getData().get(row)[2];
        System.out.println(sID + "-" + nombre + "-" + dni);
        int id = Integer.parseInt(sID);
        Usuario usuario = new Usuario(nombre, dni);
        try {
            dao.actualizarUsuarioPorId(usuario, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void borrarFila() {
        int row = vista.getTabla().getSelectedRow(); //fila seleccionada
       // System.out.printf("Fila borrada %d%n", row);
        String sID = ((ModeloTabla) vista.getTabla().
                getModel()).getData().get(row)[0]; //obtener la celda del ID
        int id = Integer.parseInt(sID); //convierto el id de String a int
        ((ModeloTabla) vista.getTabla().getModel()).getData().remove(row); //elimino la fila de data
        ((ModeloTabla) vista.getTabla().getModel()).fireTableDataChanged(); //repinta la tabla
        try {
            dao.eliminarUsuarioPorId(id); //eliminación usuario de la BD
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void iniciarApp() {
        vista.setVisible(true);
    }
}
