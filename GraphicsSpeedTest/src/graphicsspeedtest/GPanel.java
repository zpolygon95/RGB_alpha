package graphicsspeedtest;

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author Polygon
 */
public class GPanel extends JPanel
{
    Timer timer;
    ArrayList<DrawingObject> objs;
    MyTimerTask task;
    long startTime;
    long endTime;
    long result;
    long average;
    long iterations;
    
    ArrayList<Long> results;
    /**
     *
     * Default Constructor
     */
    public GPanel() {
        objs = new ArrayList<DrawingObject>();
        results = new ArrayList<Long>();
        task = new MyTimerTask();
        this.requestFocusInWindow();
        this.addKeyListener(new KL());
//        for (int i = 0; i < 10000; i++)
//        {
//            objs.add(new DrawingObject(500, 500, 3, 3));
//        }
        
        for (int i = 0; i < 10000; i++)
        {
            objs.add(new DrawingObject(new int[]{550, 560, 570, 560, 550}, new int[]{400, 410, 420, 430, 440}, 5));
        }
        
//        for (int i = 0; i < 10000; i++)
//        {
//            objs.add(new DrawingObject(750, 250, 3));
//        }
        
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 10);
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void paint(Graphics g)
    {
        for (DrawingObject o : objs)
        {
            o.paint(g);
        }
    }
    
    public class MyTimerTask extends TimerTask
    {

        @Override
        public void run() {
            repaint();
            iterations++;
            if (iterations >= 6000)
            {
                timer.cancel();
                endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
                System.out.println((endTime - startTime) / 6000);
                System.exit(0);
            }
        }
        
    }
    
    class KL implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_Q)
            {
                endTime = System.currentTimeMillis();
                result = endTime - startTime;
                average = result / iterations;
                System.out.println(average);
                System.exit(0);
            }
            System.exit(0);
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
}
