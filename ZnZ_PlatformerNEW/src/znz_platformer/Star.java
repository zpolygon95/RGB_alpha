package znz_platformer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Zach & Zac
 */
public class Star {
    
    private double originalXpos;
    private double originalYpos;
    private double currentXpos;
    private double currentYpos;

    private double width;
    private double height;
    
    private Rectangle bounds;
    
    private boolean isCollected;
    
    public Star(double originalXpos, double originalYpos) {
        this.originalXpos = originalXpos;
        this.originalYpos = originalYpos;
        currentXpos = originalXpos;
        currentYpos = originalYpos;
        isCollected = false;
        
        width = 25;
        height = 25;
        
        bounds = new Rectangle((int)currentXpos, (int)currentYpos, (int)width, (int)height);
    }
    
    public void move(double dx, double dy) {
        currentXpos += dx;
        currentYpos += dy;
        
        bounds = new Rectangle((int)currentXpos, (int)currentYpos, (int)width, (int)height);
    }
    
    public void teleport() {
        currentXpos = originalXpos;
        currentYpos = originalYpos;
        bounds = new Rectangle((int)currentXpos, (int)currentYpos, (int)width, (int)height);
    }
    
    public boolean isCollected() {
        return isCollected;
    }
    
    public void collect() {
        isCollected = true;
    }
    
    public void reset() {
        isCollected = false;
    }
    
    public Rectangle getBounds() {
        return this.bounds;
    }
    
    public void paint(Graphics g) {
        if (!isCollected){
            g.drawImage(Structures.getStarImage(), (int)currentXpos, (int)currentYpos, null);
        }
    }
}