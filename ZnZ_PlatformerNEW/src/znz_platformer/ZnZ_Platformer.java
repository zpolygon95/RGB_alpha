package znz_platformer;

import javax.swing.JFrame;

/**
 *
 * @author Zach & Zac
 */
public class ZnZ_Platformer extends JFrame{

    public ZnZ_Platformer() {
        add(new Board());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(1275, 765);
        setLocation(0, 0);
        setTitle("Zac & Zach platformer!");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setVisible(true);        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ZnZ_Platformer();
    }
}
