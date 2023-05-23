import controlador.Controlador;
import modelo.modelo.dao.UsuarioDAO;
import modelo.modelo.dao.UsuarioDAOImp;
import vista.app5.App5;

public class App {
    public static void main(String[] args) {
        App5 vista = new App5();
        UsuarioDAO modelo = new UsuarioDAOImp();
        Controlador controlador = new Controlador( modelo, vista);
    }
}
