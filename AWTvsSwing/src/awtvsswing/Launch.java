package awtvsswing;

import java.awt.Frame;
import javax.swing.JFrame;

/**
 *
 * @author Polygon
 */
public class Launch {
    
    public static JFrame swing = new JFrame();
    public static Frame awt = new Frame();
    
    public Launch() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        awt.setSize(500, 500);
        awt.setTitle("AWT");
        awt.setLocationRelativeTo(null);
        awt.setVisible(true);
        
        swing.setSize(500, 500);
        swing.setTitle("Swing");
        swing.setLocationRelativeTo(awt);
        swing.setVisible(true);
    }
}
