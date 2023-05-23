package controlador;

import modelo.modelo.dao.UsuarioDAO;
import vista.app5.App5;

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

    }

    private void borrarFila() {
        int row = vista.getTabla().getSelectedRow(); //fila seleccionada
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
