package rgb_alpha;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class specifies a platform object. It is meant to be used as a plain platform, and as the superclass of all other types of platform.
 * @author Polygon
 */
public class Platform
{
    /**
     * The x coordinate of the platform in relation to the absolute coordinate system
     */
    private int absoluteX;
    /**
     * The y coordinate of the platform in relation to the absolute coordinate system
     */
    private int absoluteY;
    /**
     * The width of the platform
     */
    private int width;
    /**
     * The height of the platform
     */
    private int height;
    /**
     * The Rectangular bounding box of the platform
     */
    private Rectangle bounds;
    /**
     * The color of the platform
     */
    protected Color color;
    
    /**
     *
     * Default constructor of the Platform object: creates a plain platform with the specified x, y, width, and height values
     * @param x the x coordinate of the platform
     * @param y the y coordinate of the platform
     * @param w the width of the platform
     * @param h the height of the platform
     */
    public Platform(int x, int y, int w, int h)
    {
        absoluteX = x;
        absoluteY = y;
        width = w;
        height = h;
        bounds  = new Rectangle(absoluteX, absoluteY, width, height);
        color = Color.BLACK;
    }
    
    /**
     * Gets the rectangular bounds of the platform
     * @return the bounds of the platform
     */
    public Rectangle getBounds()
    {
        return bounds;
    }
    
    /**
     * Gets the type of the platform being called. A convenience method replacing the getClass method.
     * @return the type of the platform being called
     */
    public String getType()
    {
        return "Plain";
    }
    
    /**
     * Paints the platform if the camera is in range.
     * @param g the Graphics context that the platform will be painted to
     * @param xOffset the x position of the camera: used to determine if the platform should be drawn
     * @param yOffset the y position of the camera: used to determine if the platform should be drawn
     */
    public void paint(Graphics g, int xOffset, int yOffset, int windowWidth, int windowHeight)
    {
        Rectangle cameraBounds = new Rectangle(xOffset, yOffset, windowWidth, windowHeight);
        if (cameraBounds.intersects(bounds) || cameraBounds.contains(bounds))
        {
            g.setColor(color);
            g.fillRect(absoluteX - xOffset, absoluteY - yOffset, width, height);
        }
    }
}
