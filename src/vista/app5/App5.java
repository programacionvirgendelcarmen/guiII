package vista.app5;

import javax.swing.*;
import java.awt.*;

public class App5 extends JFrame{
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel panelInferior;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable tabla;

    public App5() {
        tabla.setModel(new ModeloTabla());
        Container container = getContentPane();
        container.add(mainPanel);
        setTitle("Ejemplo de tabla");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700,700);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        (new App5()).setVisible(true);
    }
}
