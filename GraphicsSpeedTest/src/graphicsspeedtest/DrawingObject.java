package graphicsspeedtest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Polygon
 */
class DrawingObject
{
    private int state;
    public static final int RECT = 1;
    public static final int CIRC = 2;
    public static final int POLY = 3;
    public static final int IMG = 4;
    
    Rectangle rect;
    Rectangle circ;
    Polygon poly;
    Image img;
    
    Color color;
    
    int imageX, imageY;
    
    Random rand = new Random();
    
    /**
     * Constructs a rectangular DrawingObject
     * @param x The x coordinate of the top left corner of the rectangle
     * @param y The y coordinate of the top left corner of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public DrawingObject(int x, int y, int width, int height)
    {
        int r = rand.nextInt(241) + 10;
        int g = rand.nextInt(241) + 10;
        int b = rand.nextInt(241) + 10;
        color = new Color(r, g, b);
        
        rect = new Rectangle(x, y, width, height);
        circ = null;
        poly = null;
        img = null;
        state = RECT;
    }
    
    /**
     * Constructs a circular DrawingObject
     * @param x The x coordinate of the center point of the circle
     * @param y The y coordinate of the center point of the circle
     * @param radius The length of the radius of the circle
     */
    public DrawingObject(int x, int y, int radius)
    {
        int r = rand.nextInt(241) + 10;
        int g = rand.nextInt(241) + 10;
        int b = rand.nextInt(241) + 10;
        color = new Color(r, g, b);
        
        rect = null;
        circ = new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
        poly = null;
        img = null;
        state = CIRC;
    }
    
    /**
     * Constructs a polygonal DrawingObject
     * @param x An array of x coordinates of the polygon
     * @param y An array of y coordinates of the polygon
     * @param n the number of vertices that the polygon has
     */
    public DrawingObject(int[] x, int[] y, int n)
    {
        int r = rand.nextInt(241) + 10;
        int g = rand.nextInt(241) + 10;
        int b = rand.nextInt(241) + 10;
        color = new Color(r, g, b);
        
        rect = null;
        circ = null;
        poly = new Polygon(x, y, n);
        img = null;
        state = POLY;
    }
    
    /**
     * Constructs an image based DrawingObject
     * @param image The image of the DrawingObject
     * @param x The x coordinate of the top left corner of the object
     * @param y The y coordinate of the top left corner of the object
     */
    public DrawingObject(Image image, int x, int y)
    {
        int r = rand.nextInt(241) + 10;
        int g = rand.nextInt(241) + 10;
        int b = rand.nextInt(241) + 10;
        color = new Color(r, g, b);
        
        rect = null;
        circ = null;
        poly = null;
        img = image;
        state = IMG;
        
        imageX = x;
        imageY = y;
    }
            
    /**
     * Paints the DrawingObject in the specified graphics context
     * @param g the Graphics context in which the object is to be drawn
     */
    public void paint(Graphics g)
    {
        move();
        
        g.setColor(color);
        switch(state)
        {
            case RECT:
                g.fillRect(rect.x, rect.y, rect.width, rect.height);
                break;
            case CIRC:
                g.fillOval(circ.x, circ.y, circ.width, circ.height);
                break;
            case POLY:
                g.fillPolygon(poly);
                break;
            case IMG:
                g.drawImage(img, imageX, imageY, null);
                break;
            default:
                System.out.println("You herpd da derp");
                break;
        }
    }
    
    /**
     * This method transforms the object in a random way, based on the type of object it is
     * 
     * Rectangle - Changes the coordinates of the top left corner up to three pixels in any direction
     * Circle    - Changes the coordinates of the center point up to three pixels in any direction
     * Polygon   - Changes the coordinates of all the vertices in the polygon up to three pixels in any direction without relation to any other vertex
     * Image     - Changes coordinates of the top left corner of the image up to three pixels in any direction
     */
    private void move()
    {
        switch(state)
        {
            case RECT:
                int rx = rect.x;
                int ry = rect.y;
                int rwidth = rect.width;
                int rheight = rect.height;
                rx += (rand.nextInt(7) - 3);
                ry += (rand.nextInt(7) - 3);
                rect = new Rectangle(rx, ry, rwidth, rheight);
                break;
            case CIRC:
                int cx = circ.x;
                int cy = circ.y;
                int cwidth = circ.width;
                int cheight = circ.height;
                cx += (rand.nextInt(7) - 3);
                cy += (rand.nextInt(7) - 3);
                circ = new Rectangle(cx, cy, cwidth, cheight);
                break;
            case POLY:
                for (int i = 0; i < poly.npoints; i++)
                {
                    poly.xpoints[i] += (rand.nextInt(7) - 3);
                    poly.ypoints[i] += (rand.nextInt(7) - 3);
                }
                break;
            case IMG:
                imageX += (rand.nextInt(7) - 3);
                imageY += (rand.nextInt(7) - 3);
                break;
            default:
                System.out.println("You herpd da derp");
                break;
        }
    }
}
