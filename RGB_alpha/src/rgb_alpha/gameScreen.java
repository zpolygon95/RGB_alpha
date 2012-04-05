package rgb_alpha;

import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 *
 * @author Polygon
 */
public class gameScreen extends JPanel
{
    
    
    /**
     *
     * Default Constructor of gameScreen
     */
    public gameScreen()
    {
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        
    }
    
    public class TimedTasks extends TimerTask
    {

        @Override
        public void run() {
            repaint();
        }
        
    }
    
    public class myKeyListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    public class myMouseListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    
    public class myMouseMotionListener implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }
        
    }
    
    public class myMouseWheelListener implements MouseWheelListener
    {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            
        }
        
    }
}
