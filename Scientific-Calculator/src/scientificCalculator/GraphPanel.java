package scientificCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphPanel extends JFrame {

    JTextField function;
    PlotArea plot;

    public GraphPanel(){

        setTitle("Graph Plotter");
        setSize(800,600);
        setLayout(new BorderLayout());

        function = new JTextField("Math.sin(x)");

        plot = new PlotArea(function);

        JButton plotBtn = new JButton("Plot");

        JPanel top = new JPanel(new BorderLayout());
        top.add(function,BorderLayout.CENTER);
        top.add(plotBtn,BorderLayout.EAST);

        add(top,BorderLayout.NORTH);
        add(plot,BorderLayout.CENTER);

        plotBtn.addActionListener(e -> plot.repaint());

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class PlotArea extends JPanel implements MouseWheelListener, MouseMotionListener {

    JTextField function;

    double scale = 40;
    double offsetX = 0;
    double offsetY = 0;

    int lastX,lastY;

    PlotArea(JTextField f){
        function = f;

        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        // draw grid
        g2.setColor(Color.LIGHT_GRAY);

        for(int i=0;i<w;i+=scale)
            g2.drawLine(i,0,i,h);

        for(int j=0;j<h;j+=scale)
            g2.drawLine(0,j,w,j);

        // axis
        g2.setColor(Color.BLACK);

        g2.drawLine(0,(int)(h/2+offsetY),w,(int)(h/2+offsetY));
        g2.drawLine((int)(w/2+offsetX),0,(int)(w/2+offsetX),h);

        g2.setColor(Color.RED);

        int prevX=0,prevY=0;
        boolean first=true;

        for(double x=-10;x<=10;x+=0.05){

            try{

                String expr = function.getText().replace("x","("+x+")");

                double y = ExpressionParser.evaluate(function.getText(),x);

                int px = (int)(w/2 + x*scale + offsetX);
                int py = (int)(h/2 - y*scale + offsetY);

                if(first){
                    first=false;
                }else{
                    g2.drawLine(prevX,prevY,px,py);
                }

                prevX=px;
                prevY=py;

            }catch(Exception e){}
        }
    }

    // zoom
    public void mouseWheelMoved(MouseWheelEvent e){

        if(e.getWheelRotation()<0)
            scale*=1.1;
        else
            scale/=1.1;

        repaint();
    }

    // drag graph
    public void mouseDragged(MouseEvent e){

        offsetX += e.getX()-lastX;
        offsetY += e.getY()-lastY;

        lastX=e.getX();
        lastY=e.getY();

        repaint();
    }

    public void mouseMoved(MouseEvent e){

        lastX=e.getX();
        lastY=e.getY();
    }
}