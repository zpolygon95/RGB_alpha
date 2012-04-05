package rgb_alpha;

import javax.swing.JFrame;

/**
 *
 * @author Polygon
 */
public class RGB_alpha_Launcher extends JFrame{
    
    public RGB_alpha_Launcher() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("RGB_alpha");
        this.add(new gameScreen());
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new RGB_alpha_Launcher();
    }
}
