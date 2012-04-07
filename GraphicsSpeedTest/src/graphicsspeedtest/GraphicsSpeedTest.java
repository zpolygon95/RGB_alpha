package graphicsspeedtest;

import javax.swing.JFrame;

/**
 *
 * @author Polygon
 */
public class GraphicsSpeedTest extends JFrame{
    
    public GraphicsSpeedTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        add(new GPanel());
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GraphicsSpeedTest();
    }
}
