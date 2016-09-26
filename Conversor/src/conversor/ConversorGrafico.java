package conversor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class ConversorGrafico extends JFrame {

    JTextField pantalla;
    double resultado;
    JPanel Numeros,Operaciones;
    boolean nuevaOperacion = true;
    String operacion;

    public ConversorGrafico() {
        super();
        setSize(350, 400);
        setTitle("Conversor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        // Caracteristicas del panel
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

        pantalla = new JTextField("0", 20);
        pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
        pantalla.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(true);
        pantalla.setBackground(Color.WHITE);
        panel.add("North", pantalla);

        Numeros = new JPanel();
        Numeros.setLayout(new GridLayout(4, 3));
        Numeros.setBorder(new EmptyBorder(4, 4, 4, 4));

        for (int n = 9; n >= 0; n--) {
            nuevoBotonNumerico("" + n);

        }
        nuevoBotonNumerico(".");
        panel.add("Center", Numeros);
        
        Operaciones = new JPanel();
        Operaciones.setLayout(new GridLayout(6, 1));
        Operaciones.setBorder(new EmptyBorder(4,4,4,4));

        nuevoBotonOperacion("Convertir");
        nuevoBotonOperacion("CE");

        panel.add("East", Operaciones);

        validate();

    }

    private void nuevoBotonNumerico(String digito) {
        JButton btn = new JButton();
        btn.setText(digito);
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                numeroPulsado(btn.getText());
            }
        });
        Numeros.add(btn);
    }

    private void numeroPulsado(String digito) {
        if (pantalla.getText().equals("0") || nuevaOperacion) {
            pantalla.setText(digito);
        } else {
            pantalla.setText(pantalla.getText() + digito);
        }
        nuevaOperacion = false;
    }

    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.setForeground(Color.DARK_GRAY);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });

        Operaciones.add(btn);
    }

    private void operacionPulsado(String dato) {
        if (dato.equals("Convertir")) {
            resultado = new Double(pantalla.getText());
            calcularOperacion();
        } else if (dato.equals("CE")) {
            pantalla.setText("0");
            resultado = 0;
            nuevaOperacion = true;
        } else {
            operacion = dato;
            if ((resultado > 0) && !nuevaOperacion) {
                calcularOperacion();
            } else {
                resultado = new Double(pantalla.getText());
            }
        }

        nuevaOperacion = true;
    }


private void calcularOperacion(){
       
        resultado = (resultado/19);
        
        pantalla.setText("" + resultado);
        operacion = "";
        
            
        
    }
}