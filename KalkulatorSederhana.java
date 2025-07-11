import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KalkulatorSederhana extends JFrame implements ActionListener {
    JTextField textField;
    JButton[] buttons;
    String[] labels = {
        "1", "2", "3", "4", "5", "6",
        "7", "8", "9", "0", "+", "-",
        "*", "/", "=", "%", "Mod", "Exit"
    };

    public KalkulatorSederhana() {
        setTitle("Kalkulator Sederhana");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text field untuk input/output
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        // Panel tombol
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 6, 5, 5)); // 3 baris, 6 kolom
        buttons = new JButton[labels.length];

        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Exit")) {
            System.exit(0);
        } else if (command.equals("=")) {
            try {
                String result = String.valueOf(evaluate(textField.getText()));
                textField.setText(result);
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else if (command.equals("Mod")) {
            textField.setText(textField.getText() + "%");
        } else {
            textField.setText(textField.getText() + command);
        }
    }

    // Evaluasi ekspresi matematika sederhana (tanpa prioritas operator)
    public static double evaluate(String expr) {
        expr = expr.replaceAll("Mod", "%");
        expr = expr.replaceAll("[^0-9+\\-*/%.]", ""); // sanitasi input
        char[] tokens = expr.toCharArray();

        double current = 0;
        double result = 0;
        char op = '+';
        String number = "";

        for (int i = 0; i < tokens.length; i++) {
            char ch = tokens[i];
            if (Character.isDigit(ch) || ch == '.') {
                number += ch;
            }
            if (!Character.isDigit(ch) && ch != '.' || i == tokens.length - 1) {
                double num = Double.parseDouble(number);
                switch (op) {
                    case '+': result += num; break;
                    case '-': result -= num; break;
                    case '*': result *= num; break;
                    case '/': result /= num; break;
                    case '%': result %= num; break;
                }
                op = ch;
                number = "";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        new KalkulatorSederhana();
    }
}
