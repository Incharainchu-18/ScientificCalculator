package scientificCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EquationSolver extends JFrame implements ActionListener {

    JTextField a,b,c;
    int A,B,C;
    JLabel result;

    public EquationSolver(){

        setTitle("Quadratic Equation Solver");

        setSize(300,200);
        setLayout(new GridLayout(5,2));

        add(new JLabel("a:"));
        a=new JTextField();

        add(a);

        add(new JLabel("b:"));
        b=new JTextField();
        add(b);

        add(new JLabel("c:"));
        c=new JTextField();
        add(c);

        JButton solve=new JButton("Solve");

        solve.addActionListener(this);

        add(solve);

        result=new JLabel("");

        add(result);

        setVisible(true);
        double d = B*B - 4*A*C;
        
        if(d > 0){
        	double x1 = (-B + Math.sqrt(d))/(2*A);
        	double x2 = (-B - Math.sqrt(d))/(2*A);
        	result.setText("x1 = " + x1 + "   x2 = " + x2);
        }
        else if(d == 0){
        	double x = -B/(2*A);
        	result.setText("x = " + x);
        }
        else{
        	double real = -B/(2*A);
        	double imag = Math.sqrt(-d)/(2*A);
        	result.setText("x1 = " + real + " + " + imag + "i , x2 = " + real + " - " + imag + "i");
        }
    }


    public void actionPerformed(ActionEvent e) {

    	    try {

    	        double A = Double.parseDouble(a.getText());
    	        double B = Double.parseDouble(b.getText());
    	        double C = Double.parseDouble(c.getText());

    	        double d = B*B - 4*A*C;

    	        if(d < 0){
    	            result.setText("No real roots");
    	            return;
    	        }

    	        double x1 = (-B + Math.sqrt(d))/(2*A);
    	        double x2 = (-B - Math.sqrt(d))/(2*A);

    	        result.setText("x1 = " + x1 + "   x2 = " + x2);

    	    } catch(NumberFormatException ex) {

    	        JOptionPane.showMessageDialog(
    	                this,
    	                "Please enter valid numbers only!",
    	                "Invalid Input",
    	                JOptionPane.ERROR_MESSAGE
    	        );

    	    }
    	}
    }
