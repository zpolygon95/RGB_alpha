package rgb_alpha;

import javax.swing.JPanel;
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
 * The JPanel subclass that is used to draw everything in the game and delegate all user input
 * @author Polygon
 */
public class gameScreen extends JPanel
{
    
    
    /**
     *
     * Default Constructor of the gameScreen
     */
    public gameScreen()
    {
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        
    }
    
    /**
     * The class the processes key events
     */
    public class myKeyListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            
        }
    }
    
    /**
     * The class that processes mouse events
     */
    public class myMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            
        }
    }
    
    /**
     * The class that processes mouse motion events
     */
    public class myMouseMotionListener implements MouseMotionListener
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            
        }
    }
    
    /**
     * The class that processes mouse wheel events
     */
    public class myMouseWheelListener implements MouseWheelListener
    {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            
        }
    }
}
