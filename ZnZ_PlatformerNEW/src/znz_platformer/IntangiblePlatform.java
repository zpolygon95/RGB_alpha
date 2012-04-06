package znz_platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Zac & Zach
 */
public class IntangiblePlatform extends Platform{
    
    /**
     * constructs a platform the player cannot move through with the following parameters
     * @param originalXPos the original x position of the platform
     * @param originalYPos the original y position of the platform
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public IntangiblePlatform(double originalXPos, double originalYPos, double width, double height) {
        super(originalXPos, originalYPos, width, height);
        PFType = INTANGIBLE;
    }
    /**
     * paints the platform represented by this class
     * @param g the graphics that should be passed to this method
     */
    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.red);
        g.fillRect(getBounds().x,
                getBounds().y,
                getBounds().width,
                getBounds().height);
    }
}
