package graphicsspeedtest;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author Polygon
 */
public class GPanel extends Canvas
{
    Timer timer;
    ArrayList<DrawingObject> objs;
    long startTime;
    long endTime;
    long result;
    long average;
    long iterations;
    Image backupImage;
    
    ArrayList<Long> results;
    /**
     *
     * Default Constructor
     */
    public GPanel() {
        objs = new ArrayList<DrawingObject>();
        results = new ArrayList<Long>();
        this.requestFocusInWindow();
        this.addKeyListener(new KL());
        for (int i = 0; i < 10; i++)
        {
            objs.add(new DrawingObject(500, 500, 3, 3));
        }
        
        for (int i = 0; i < 10; i++)
        {
            objs.add(new DrawingObject(new int[]{550, 560, 570, 560, 550}, new int[]{400, 410, 420, 430, 440}, 5));
        }
        
        for (int i = 0; i < 10; i++)
        {
            objs.add(new DrawingObject(750, 250, 3));
        }
        
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 10);
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backupImage, 0, 0, this);
        for (DrawingObject o : objs)
        {
            o.paint(g);
        }
        timer.schedule(new MyTimerTask(), 20);
    }
    
    public class MyTimerTask extends TimerTask
    {

        @Override
        public void run() {
            backupImage = createImage(WIDTH, HEIGHT);
            iterations++;
            if (iterations >= 6000)
            {
                timer.cancel();
                endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
                System.out.println((endTime - startTime) / 6000);
                System.exit(0);
            }
            repaint();
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
