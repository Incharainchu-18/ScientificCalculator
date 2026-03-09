package scientificCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainCalculator extends JFrame implements ActionListener {

    JTextField display;
    JTextArea history;

    public MainCalculator(){

        setTitle("Advanced Scientific Calculator");
        setSize(700,500);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial",Font.BOLD,22));
        display.setHorizontalAlignment(JTextField.RIGHT);

        add(display,BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6,5,5,5));

        String buttons[] = {
                "7","8","9","/","sin",
                "4","5","6","*","cos",
                "1","2","3","-","tan",
                "0",".","+","=","√",
                "Graph","Matrix","SolveEq","History","Clear"
        };

        for(String b : buttons){

            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial",Font.BOLD,14));
            btn.addActionListener(this);

            buttonPanel.add(btn);
        }

        add(buttonPanel,BorderLayout.CENTER);

        history = new JTextArea();
        history.setEditable(false);

        add(new JScrollPane(history),BorderLayout.EAST);

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){

        String cmd = e.getActionCommand();

        try{

            switch(cmd){

                case "=":

                    String expr = display.getText();

                    double result = evalBasic(expr);

                    history.append(expr + " = " + result + "\n");

                    display.setText(""+result);

                    break;

                case "sin":

                    double s = Math.sin(Double.parseDouble(display.getText()));
                    display.setText(""+s);
                    break;

                case "cos":

                    double c = Math.cos(Double.parseDouble(display.getText()));
                    display.setText(""+c);
                    break;

                case "tan":

                    double t = Math.tan(Double.parseDouble(display.getText()));
                    display.setText(""+t);
                    break;

                case "√":

                    double r = Math.sqrt(Double.parseDouble(display.getText()));
                    display.setText(""+r);
                    break;

                case "Graph":

                    new GraphPanel();
                    break;

                case "Matrix":

                    new MatrixCalculator();
                    break;

                case "SolveEq":

                    new EquationSolver();
                    break;

                case "Clear":

                    display.setText("");
                    break;

                case "History":

                    JOptionPane.showMessageDialog(this,new JScrollPane(history),
                            "Calculation History",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:

                    display.setText(display.getText()+cmd);
            }

        }catch(Exception ex){

            display.setText("Error");
        }
    }

    double evalBasic(String expr){

        if(expr.contains("+")){

            String p[] = expr.split("\\+");
            return Double.parseDouble(p[0]) + Double.parseDouble(p[1]);
        }

        if(expr.contains("-")){

            String p[] = expr.split("\\-");
            return Double.parseDouble(p[0]) - Double.parseDouble(p[1]);
        }

        if(expr.contains("*")){

            String p[] = expr.split("\\*");
            return Double.parseDouble(p[0]) * Double.parseDouble(p[1]);
        }

        if(expr.contains("/")){

            String p[] = expr.split("\\/");
            return Double.parseDouble(p[0]) / Double.parseDouble(p[1]);
        }

        return Double.parseDouble(expr);
    }

    public static void main(String[] args){

        new MainCalculator();
    }
}