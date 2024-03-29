package znz_platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Zac & Zach
 */
public class Platform {
    
    public boolean playerIsOnTop = false;
    
    
    private double originalXpos;
    private double originalYpos;
    private double width;
    private double height;
    
    protected double currentXpos;
    protected double currentYpos;
    
    protected Rectangle bounds;
    
    public int PFType;
    
    public final int REGULAR = 0;
    public final int INTANGIBLE = 1;
    public final int MOVING = 2;
    public final int JUMP_PAD = 3;
    
    /**
     * constructs a platform the player cannot move through with the following parameters
     * @param originalXPos the original x position of the platform
     * @param originalYPos the original y position of the platform
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public Platform(double originalXPos, double originalYPos, double width, double height) {
        this.originalXpos = originalXPos;
        this.originalYpos = originalYPos;
        this.width = width;
        this.height = height;
        this.currentXpos = originalXPos;
        this.currentYpos = originalYPos;
        this.bounds = new Rectangle((int)originalXPos, (int)originalYPos, (int)width, (int)height);
        PFType = REGULAR;
    }
    /**
     * moves the structures base on player velocity
     * @param dx the change in x
     * @param dy the chance in y
     */
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
    
    /**
     * used in the drawing of the platforms on the board
     * @return the bounds of the rectangle representing the platform
     */
    public Rectangle getBounds() {
        return bounds;
    }
    
    public int getX() {
        return (int)currentXpos;
    }
    
    public double getMinX() {
        return bounds.getMinX();
    }
    
    public double getMaxX() {
        return bounds.getMaxX();
    }
    
    public int getY() {
        return (int)currentYpos;
    }
    
    public double getMaxY() {
        return bounds.getMaxY();
    }
    
    public double getMinY() {
        return bounds.getMinY();
    }
    
    public int getWidth() {
        return (int)width;
    }
    
    public int getHeight() {
        return (int)height;
    }
    
    public void Scroll() {
    }
    
    /**
     * paints the platform represented by this class
     * @param g the graphics that should be passed to this method
     */
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
