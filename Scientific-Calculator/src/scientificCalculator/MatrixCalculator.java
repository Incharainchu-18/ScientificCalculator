package scientificCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatrixCalculator extends JFrame implements ActionListener {

    JTextField a[][] = new JTextField[2][2];
    JTextField b[][] = new JTextField[2][2];

    JTextArea result;

    JButton addBtn, subBtn, mulBtn, detBtn, transBtn;

    public MatrixCalculator(){

        setTitle("Advanced Matrix Calculator");
        setSize(450,400);
        setLayout(new BorderLayout());

        JPanel matrixPanel = new JPanel(new GridLayout(2,2));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.setBorder(BorderFactory.createTitledBorder("Matrix A"));
        p2.setBorder(BorderFactory.createTitledBorder("Matrix B"));

        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++){
                a[i][j] = new JTextField(3);
                p1.add(a[i][j]);
            }

        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++){
                b[i][j] = new JTextField(3);
                p2.add(b[i][j]);
            }

        matrixPanel.add(p1);
        matrixPanel.add(p2);

        add(matrixPanel,BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        addBtn = new JButton("Add");
        subBtn = new JButton("Subtract");
        mulBtn = new JButton("Multiply");
        detBtn = new JButton("Determinant A");
        transBtn = new JButton("Transpose A");

        buttonPanel.add(addBtn);
        buttonPanel.add(subBtn);
        buttonPanel.add(mulBtn);
        buttonPanel.add(detBtn);
        buttonPanel.add(transBtn);

        addBtn.addActionListener(this);
        subBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        detBtn.addActionListener(this);
        transBtn.addActionListener(this);

        add(buttonPanel,BorderLayout.CENTER);

        result = new JTextArea(6,20);
        result.setFont(new Font("Arial",Font.BOLD,16));
        result.setEditable(false);

        add(new JScrollPane(result),BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    int[][] getMatrix(JTextField m[][]){

        int mat[][] = new int[2][2];

        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                mat[i][j] = Integer.parseInt(m[i][j].getText());

        return mat;
    }

    public void actionPerformed(ActionEvent e){

        try{

            int A[][] = getMatrix(a);
            int B[][] = getMatrix(b);
            int C[][] = new int[2][2];

            result.setText("");

            if(e.getSource()==addBtn){

                for(int i=0;i<2;i++)
                    for(int j=0;j<2;j++)
                        C[i][j]=A[i][j]+B[i][j];

                printMatrix(C);

            }

            else if(e.getSource()==subBtn){

                for(int i=0;i<2;i++)
                    for(int j=0;j<2;j++)
                        C[i][j]=A[i][j]-B[i][j];

                printMatrix(C);
            }

            else if(e.getSource()==mulBtn){

                for(int i=0;i<2;i++)
                    for(int j=0;j<2;j++){

                        C[i][j]=0;

                        for(int k=0;k<2;k++)
                            C[i][j]+=A[i][k]*B[k][j];
                    }

                printMatrix(C);
            }

            else if(e.getSource()==detBtn){

                int det = A[0][0]*A[1][1] - A[0][1]*A[1][0];

                result.setText("Determinant of Matrix A = " + det);
            }

            else if(e.getSource()==transBtn){

                int T[][] = new int[2][2];

                for(int i=0;i<2;i++)
                    for(int j=0;j<2;j++)
                        T[i][j] = A[j][i];

                result.setText("Transpose of Matrix A:\n");

                printMatrix(T);
            }

        }
        catch(Exception ex){

            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void printMatrix(int m[][]){

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++)
                result.append(m[i][j]+" ");
            result.append("\n");
        }
    }
}