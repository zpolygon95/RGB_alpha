package bouncyscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Polygon
 */
class ZPanel extends JPanel
{
    Polygon mainPolygon;
    ArrayList<Point> points;
    ArrayList<Point> vectors;
    Color color;
    int target;
    int fps;
    int waitTime;
    long st;
    long et;
    Random rand = new Random();
    
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    Timer timer;
    
    Image backBuffer;
    
    /**
     *
     * Default Constructor
     */
    public ZPanel(int points, int fr, Color color)
    {
        this.points = new ArrayList<Point>();
        this.vectors = new ArrayList<Point>();
        for (int i = 0; i < points; i++)
        {
            this.points.add(new Point(rand.nextInt(width + 1), rand.nextInt(height + 1)));
            this.vectors.add(new Point(rand.nextInt(5) - 2, rand.nextInt(5) - 2));
        }
        int[] x = new int[points];
        int[] y = new int[points];
        for (int i = 0; i < points; i++)
        {
            x[i] = this.points.get(i).x;
            y[i] = this.points.get(i).y;
        }
        mainPolygon = new Polygon(x, y, points);
        target = fr;
        fps = target;
        st = System.currentTimeMillis();
        et = st + 1;
        this.color = color;
        setBackground(Color.BLACK);
        timer = new Timer();
        timer.schedule(new Task(), 0);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        makeMove();
        g.setColor(color);
        g.drawPolygon(mainPolygon);
        timer.schedule(new Task(), waitTime);
    }
    
    private void makeMove()
    {
        for (int i = 0; i < points.size(); i++)
        {
            Point tempPoint = points.get(i);
            Point tempVector = vectors.get(i);
            tempPoint.x += tempVector.x;
            tempPoint.y += tempVector.y;
            points.set(i, tempPoint);
            if (tempPoint.x <= 0 || tempPoint.x >= width)
                tempVector.x *= -1;
            if (tempPoint.y <= 0 || tempPoint.y >= height)
                tempVector.y *= -1;
            vectors.set(i, tempVector);
        }
        
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];
        for (int i = 0; i < points.size(); i++)
        {
            x[i] = this.points.get(i).x;
            y[i] = this.points.get(i).y;
        }
        mainPolygon = new Polygon(x, y, points.size());
    }
    
    public class Task extends TimerTask
    {

        @Override
        public void run() {
            repaint();
            et = System.currentTimeMillis();
            fps = 10;
            if (et - st != 0)
                fps = (int) (1000 / (et - st));
            waitTime = ((1000 / target) - (1000 / fps));
            if (waitTime < 0)
                waitTime = 0;
            System.out.println((et-st) + " ms for this iteration");
            System.out.println(fps + " fps");
            System.out.println(waitTime + " ms to wait until next iteration");
            st = System.currentTimeMillis();
        }
        
    }
}
