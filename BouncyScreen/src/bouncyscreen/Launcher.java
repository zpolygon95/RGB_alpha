package bouncyscreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JDialog;

/**
 *
 * @author Polygon
 */
public class Launcher
{
    public static JFrame start, main;
    
    public static JTextField numPoints, R, G, B, frequency;
    
    public static JButton ok, exit;
    
    public static JPanel buttons, vals;
    
    public static JDialog errorBox;
    
    public static int points, r, g, b, fr;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        main = new JFrame();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        main.setUndecorated(true);
        
        start = new JFrame("Bouncy screenSaver!");
        start.setLayout(new BorderLayout(10, 10));
        
        buttons = new JPanel(new GridLayout(1, 2, 10, 10));
        ok = new JButton("Ok");
        ok.addActionListener(new OkListener());
        exit = new JButton("Exit");
        exit.addActionListener(new ExitListener());
        buttons.add(exit);
        buttons.add(ok);
        
        vals = new JPanel(new GridLayout(2, 4, 10, 10));
        numPoints = new JTextField();
        R = new JTextField();
        G = new JTextField();
        B = new JTextField();
        frequency = new JTextField();
        
        vals.add(new JLabel("Number of Points: "));
        vals.add(numPoints);
        vals.add(new JLabel("Frequency: "));
        vals.add(frequency);
        vals.add(new JLabel("Color (R, G, B): "));
        vals.add(R);
        vals.add(G);
        vals.add(B);
        
        start.add(vals, BorderLayout.CENTER);
        start.add(buttons, BorderLayout.SOUTH);
        
        start.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        start.pack();
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        start.setResizable(false);
        
        errorBox = new JDialog();
        errorBox.setLayout(new BorderLayout(10, 10));
        errorBox.add(new JLabel("Incorrect parameters. Please make sure that you only use numerals"), BorderLayout.CENTER);
        JButton okBtn = new JButton("Ok");
        okBtn.addActionListener(new ErrOkListener());
        errorBox.add(okBtn, BorderLayout.SOUTH);
        errorBox.setTitle("Error!");
        errorBox.pack();
        errorBox.setResizable(false);
        errorBox.setAlwaysOnTop(true);
        errorBox.setLocationRelativeTo(null);
    }
    
    public static class OkListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                points = Integer.parseInt(numPoints.getText());
                fr = Integer.parseInt(frequency.getText());
                r = Integer.parseInt(R.getText());
                g = Integer.parseInt(G.getText());
                b = Integer.parseInt(B.getText());
                
                Color color = new Color(r, g, b);
                
                main.add(new ZPanel(points, fr, color));
                main.setVisible(true);
                start.setVisible(false);
            }
            catch(NumberFormatException err)
            {
                errorBox.setLocationRelativeTo(null);
                errorBox.setVisible(true);
                Toolkit.getDefaultToolkit().beep();
            }
        }   
    }
    
    public static class ExitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    public static class ErrOkListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            errorBox.setVisible(false);
        }
    }
}
